package com.tachung.server.DTO;

import lombok.Data;

import java.util.List;

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
