package com.tachung.server.DTO;

import lombok.Data;

import java.time.LocalDate;

// OTT 서비스 날짜 DTO
@Data
public class StreamingServiceDateDTO {
    private String servicename;
    private LocalDate servicedate;

}
