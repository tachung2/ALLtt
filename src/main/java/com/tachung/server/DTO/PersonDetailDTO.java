package com.tachung.server.DTO;

import lombok.Data;

import java.util.List;

// 드라마 출연진 정보 DTO
@Data
public class PersonDetailDTO {
    private List<Person> cast;
    private List<Person> crew;

    @Data
    public static class Person {
        private String character;
        private String profilePath;
    }
}
