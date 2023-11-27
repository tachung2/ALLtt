package com.tachung.server.service;

import com.tachung.server.DTO.StreamingServiceDateDTO;
import com.tachung.server.Entity.StreamingServiceDate;
import com.tachung.server.Entity.User;
import com.tachung.server.Repository.StreamingServiceDateRepository;
import com.tachung.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StreamingServiceDateService {

    // StreamingServiceDateRepository 인스턴스를 주입받아 데이터베이스 작업을 수행합니다.
    private final StreamingServiceDateRepository repository;

    // UserRepository 인스턴스를 주입받아 User 엔티티와 관련된 작업을 수행합니다.
    private final UserRepository userRepository;

    @Autowired
    public StreamingServiceDateService(StreamingServiceDateRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<StreamingServiceDateDTO> getServiceDate(Long userId) {
        List<StreamingServiceDate> existingData = repository.findAllByUserId(userId);
        List<StreamingServiceDateDTO> dtoList = new ArrayList<>();

        for (StreamingServiceDate data : existingData) {
            StreamingServiceDateDTO dto = new StreamingServiceDateDTO();
            dto.setServicename(data.getServiceName());
            dto.setServicedate(data.getServiceDate());
            dtoList.add(dto); // 변환된 DTO를 리스트에 추가
        }

        return dtoList; // DTO 리스트 반환
    }

    public void updateOrSaveServiceDate(Long userId, String serviceName, LocalDate serviceDate) {
        // user_id와 service_name을 사용하여 기존 데이터 조회
        Optional<StreamingServiceDate> existingData = repository.findByUserIdAndServiceName(userId, serviceName);
        // user_id를 사용하여 User 엔티티 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

        StreamingServiceDate serviceDateData;
        if (existingData.isPresent()) {
            // 기존 데이터가 존재하는 경우, 해당 데이터를 수정
            serviceDateData = existingData.get();
            serviceDateData.setServiceDate(serviceDate);
        } else {
            // 새 데이터 생성
            serviceDateData = new StreamingServiceDate();
            serviceDateData.setServiceName(serviceName);
            serviceDateData.setServiceDate(serviceDate);
            serviceDateData.setUser(user);
        }
        repository.save(serviceDateData);
    }

}
