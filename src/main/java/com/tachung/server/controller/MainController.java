package com.tachung.server.controller;

import com.tachung.server.DTO.*;
import com.tachung.server.domain.user.User;
import com.tachung.server.domain.user.UserRepository;
import com.tachung.server.service.*;
import com.tachung.server.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MainController {

    private final ContentsService contentsService;
    private final UserService userService;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    private final DetailService detailService;

    private final SearchService searchService;

    private final SeleniumService seleniumService;

    @Autowired
    public MainController(ContentsService contentsService, UserService userService, UserRepository userRepository, JwtUtil jwtUtil, DetailService detailService, SearchService searchService, SeleniumService seleniumService) {
        this.contentsService = contentsService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.detailService = detailService;
        this.searchService = searchService;
        this.seleniumService = seleniumService;
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

    // 넷플릭스 정보 상위 5개 가져오기
    @GetMapping("/netflex")
    public List<MovieDto> getNetflexTop5() {
        return contentsService.getNetflixTop5();
    }

    // 왓챠 정보 상위 5개 가져오기
    @GetMapping("/watcha")
    public List<MovieDto> getWatchaTop5() {
        return contentsService.getWatchaTop5();
    }

    // 디즈니 정보 상위 5개 가져오기
    @GetMapping("/disney")
    public List<MovieDto> getDisneyTop5() {
        return contentsService.getDisneyTop5();
    }

    // 티빙 정보 상위 5개 가져오기
    @GetMapping("/tving")
    public List<MovieDto> getTvingTop5() {
        return contentsService.getTvingTop5();
    }

    // 웨이브 정보 상위 5개 가져오기
    @GetMapping("/wavve")
    public List<MovieDto> getWavveTop5() {
        return contentsService.getWavveTop5();
    }

    // 애플+ 정보 상위 5개 가져오기
    @GetMapping("/apple")
    public List<MovieDto> getAppleTop5() {
        return contentsService.getAppleTop5();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ottDetail> getMovieDetail(@PathVariable String id) {
        return ResponseEntity.ok(detailService.getMovieDetail(id));
    }

    @GetMapping("/detail/videos/{id}")
    public ResponseEntity<ottDetail> getVideoDetail(@PathVariable String id) {
        return ResponseEntity.ok(detailService.getVideoDetail(id));
    }

    @GetMapping("/search/both")
    public Map<String, Object> searchBoth(@RequestParam String query) {
        return searchService.searchBoth(query);
    }

    @GetMapping("/tv")
    public List<MovieDto> searchTvShows(@RequestParam String query) {
        return searchService.searchTvShows(query);
    }

    @GetMapping("/person")
    public List<Person> searchPersons(@RequestParam String query) {
        return searchService.searchPersons(query);
    }

    @PostMapping("/netflix/subscriptionDate")
    public String getSubscriptionDate(@RequestBody UserRequest userRequest) {
        return seleniumService.getSubscriptionDate(userRequest.getEmail(), userRequest.getPassword());
    }

}
