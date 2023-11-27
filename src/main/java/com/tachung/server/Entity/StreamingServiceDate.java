package com.tachung.server.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

// OTT 서비스 날짜 Entity
@Data
@Entity
public class StreamingServiceDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private LocalDate serviceDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
