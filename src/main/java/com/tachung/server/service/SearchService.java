package com.tachung.server.service;

import com.tachung.server.DTO.MovieDto;
import com.tachung.server.DTO.Person;
import com.tachung.server.DTO.PersonListResponse;
import com.tachung.server.DTO.TvShowListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    private final String API_URL = "https://api.themoviedb.org/3";
    private final String API_KEY = "d339d2e4a1c59c8499dacf75c38da31c";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<MovieDto> searchTvShows(String query) {
        String url = API_URL + "/search/tv?api_key=" + API_KEY + "&query=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&language=ko-KR";
        ResponseEntity<TvShowListResponse> response = restTemplate.getForEntity(url, TvShowListResponse.class);
        return response.getBody().getResults();
    }

    public List<Person> searchPersons(String query) {
        String url = API_URL + "/search/person?&api_key=" + API_KEY + "&query=" + URLEncoder.encode(query, StandardCharsets.UTF_8) + "&language=ko-KR";
        ResponseEntity<PersonListResponse> response = restTemplate.getForEntity(url, PersonListResponse.class);
        return response.getBody().getResults();
    }

    public Map<String, Object> searchBoth(String query) {
        List<MovieDto> tvShows = searchTvShows(query);
        List<Person> persons = searchPersons(query);

        Map<String, Object> result = new HashMap<>();
        result.put("tvShows", tvShows);
        result.put("persons", persons);
        return result;
    }
}
