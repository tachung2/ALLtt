package com.tachung.server.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


// 드라마 정보 요청 결과 DTO
@Data
@NoArgsConstructor
public class TvShowListResponse {
    private List<MovieDto> results;
}
