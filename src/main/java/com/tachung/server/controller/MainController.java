package com.tachung.server.controller;

import com.tachung.server.DTO.*;
import com.tachung.server.Entity.User;
import com.tachung.server.Repository.UserRepository;
import com.tachung.server.service.*;
import com.tachung.server.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ContentsService contentsService;
    private final UserService userService;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    private final DetailService detailService;

    private final SearchService searchService;

    private final StreamingServiceDateService streamingServiceDateService;


    @Autowired
    public MainController(ContentsService contentsService, UserService userService, UserRepository userRepository, JwtUtil jwtUtil, DetailService detailService, SearchService searchService, StreamingServiceDateService streamingServiceDateService) {
        this.contentsService = contentsService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.detailService = detailService;
        this.searchService = searchService;
        this.streamingServiceDateService = streamingServiceDateService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            userService.signup(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            // 이메일과 비밀번호가 일치하는 경우
            String accessToken = jwtUtil.generateToken(user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            logger.info(accessToken);
            UserInfo userInfo = new UserInfo(existingUser.get().getName(), existingUser.get().getEmail());
            return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken, userInfo));  // 200 상태 코드 반환
        }
        // 이메일이 없거나 비밀번호가 일치하지 않는 경우
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 401 상태 코드 반환
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuthenticationResponse {
        private String accessToken;
        private String refreshToken;
        private UserInfo userInfo;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Refresh-Token") String refreshToken) {
        // Refresh Token 유효성 검사
        if (jwtUtil.validateToken(refreshToken)) {
            String email = jwtUtil.extractEmail(refreshToken);
            String newAccessToken = jwtUtil.generateToken(email);
            return ResponseEntity.ok(new TokenResponse(newAccessToken, null)); // 새로운 Refresh Token은 발급하지 않음
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TokenResponse {
        private String accessToken;
        private String refreshToken;
    }

    @GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                User user = userService.getUserByEmail(email);
                if (user != null) {
                    // 사용자 정보를 Map 혹은 DTO 객체를 사용하여 JSON 형식으로 반환
                    Map<String, String> userInfo = new HashMap<>();
                    userInfo.put("id", user.getId().toString());
                    userInfo.put("userName", user.getName());
                    return ResponseEntity.ok(userInfo);
                }
            }
        }
        return ResponseEntity.badRequest().body("Invalid Token or User not found");
    }

    // 넷플릭스 정보 상위 5개 가져오기 -> 10개
    @GetMapping("/netflix")
    public List<MovieDto> getNetflexTop5() {
        return contentsService.getNetflixTop5();
    }

    // 왓챠 정보 상위 5개 가져오기 -> 10개
    @GetMapping("/watcha")
    public List<MovieDto> getWatchaTop5() {
        return contentsService.getWatchaTop5();
    }

    // 디즈니 정보 상위 5개 가져오기 -> 10개
    @GetMapping("/disney")
    public List<MovieDto> getDisneyTop5() {
        return contentsService.getDisneyTop5();
    }

    // 티빙 정보 상위 5개 가져오기 -> 10개
    @GetMapping("/tving")
    public List<MovieDto> getTvingTop5() {
        return contentsService.getTvingTop5();
    }

    // 웨이브 정보 상위 5개 가져오기 -> 10개
    @GetMapping("/wavve")
    public List<MovieDto> getWavveTop5() {
        return contentsService.getWavveTop5();
    }

    // 애플+ 정보 상위 5개 가져오기 -> 10개
    @GetMapping("/apple")
    public List<MovieDto> getAppleTop5() {
        return contentsService.getAppleTop5();
    }

    // 드라마 세부 정보 페이지 가져오기
    @GetMapping("/detail/{id}")
    public ResponseEntity<ottDetail> getMovieDetail(@PathVariable String id) {
        return ResponseEntity.ok(detailService.getMovieDetail(id));
    }

//    드라마 트레일러 및 예고편, 비하인드 영상 가져오기
    @GetMapping("/detail/videos/{id}")
    public ResponseEntity<ottDetail> getVideoDetail(@PathVariable String id) {
        return ResponseEntity.ok(detailService.getVideoDetail(id));
    }

//    배우와 드라마 검색
    @GetMapping("/search/both")
    public Map<String, Object> searchBoth(@RequestParam String query) {
        return searchService.searchBoth(query);
    }

//    드라마 검색
    @GetMapping("/tv")
    public List<MovieDto> searchTvShows(@RequestParam String query) {
        return searchService.searchTvShows(query);
    }

//    배우 검색
    @GetMapping("/person")
    public List<Person> searchPersons(@RequestParam String query) {
        return searchService.searchPersons(query);
    }

//    사용자가 선택한 OTT 서비스의 시청 날짜 저장
    @PostMapping("/{serviceName}")
    public ResponseEntity<?> saveServiceDate(@PathVariable String serviceName, @RequestBody StreamingServiceDateDTO dto, @RequestParam Long userId) {
        dto.setServicename(serviceName);

        logger.info(ResponseEntity.ok().build().toString());
        LocalDate serviceDate = dto.getServicedate(); // dto에서 날짜 정보 추출
        streamingServiceDateService.updateOrSaveServiceDate(userId, serviceName, serviceDate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/service-dates")
    public ResponseEntity<List<StreamingServiceDateDTO>> getServiceDates(@RequestParam Long userId) {
        List<StreamingServiceDateDTO> serviceDates = streamingServiceDateService.getServiceDate(userId);
        return ResponseEntity.ok(serviceDates);
    }

}
