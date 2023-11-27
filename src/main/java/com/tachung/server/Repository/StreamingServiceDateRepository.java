package com.tachung.server.Repository;

import com.tachung.server.Entity.StreamingServiceDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StreamingServiceDateRepository extends JpaRepository<StreamingServiceDate, Long>
{

    Optional<StreamingServiceDate> findByUserIdAndServiceName(Long userId, String serviceName);

//   userID를 사용하여 ott서비스 모든 데이터 조회
    List<StreamingServiceDate> findAllByUserId(Long userId);
}
