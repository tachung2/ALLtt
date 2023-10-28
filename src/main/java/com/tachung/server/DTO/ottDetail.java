package com.tachung.server.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ottDetail {
    private Long id;
    private String name;
    private String poster_path;
    private String overview;
    private String popularity;
    private String vote_average;
    private String vote_count;
    private String first_air_date;
    private String number_of_seasons;
    private String adult;
    private List<Network> networks;
    private List<Person> created_by;
    private List<Genre> genres;
    private List<Video> results;

    @Data
    public static class Person {
        private String name;
        private String profile_path;
    }

    @Data
    public static class Network {
        private String name;
        private String logo_path;
    }

    @Data
    public static class Genre {
        private String name;
    }

    @Data
    public static class Video {
        private String key;
    }
}



