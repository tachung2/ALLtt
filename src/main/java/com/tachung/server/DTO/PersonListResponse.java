package com.tachung.server.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 드라마 출연진 정보 요청 결과 DTO
@Data
@NoArgsConstructor
public class PersonListResponse {
    private List<Person> results;
}
