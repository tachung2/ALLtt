package com.tachung.server.service;

import com.tachung.server.DTO.MovieDto;
import com.tachung.server.DTO.MovieListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentsService {
    private final String API_URL = "https://api.themoviedb.org/3";
    private final String API_KEY = "d339d2e4a1c59c8499dacf75c38da31c";

    public List<MovieDto> getNetflixTop5() {
        RestTemplate restTemplate = new RestTemplate();
        // TMDB에서 제공하는 엔드포인트 및 파라미터를 사용하여 필요한 데이터를 요청합니다.
        // 예제로는 discover 엔드포인트를 사용하였습니다.
        String url = API_URL + "/discover/tv?region=KR&language=ko-KR&with_networks=213&api_key=" + API_KEY + "&sort_by=popularity.desc&limit=10";
        ResponseEntity<MovieListResponse> response = restTemplate.getForEntity(url, MovieListResponse.class);
        return response.getBody().getResults().stream().limit(10).collect(Collectors.toList());
    }

    public List<MovieDto> getWatchaTop5() {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/discover/tv?region=KR&language=ko-KR&with_networks=3898&api_key=" + API_KEY + "&sort_by=popularity.desc&limit=10";
        ResponseEntity<MovieListResponse> response = restTemplate.getForEntity(url, MovieListResponse.class);
        return response.getBody().getResults().stream().limit(10).collect(Collectors.toList());
    }
    public List<MovieDto> getDisneyTop5() {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/discover/tv?region=KR&language=ko-KR&with_networks=2739&api_key=" + API_KEY + "&sort_by=popularity.desc&limit=10";
        ResponseEntity<MovieListResponse> response = restTemplate.getForEntity(url, MovieListResponse.class);
        return response.getBody().getResults().stream().limit(10).collect(Collectors.toList());
    }

    public List<MovieDto> getTvingTop5() {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/discover/tv?region=KR&language=ko-KR&with_networks=3897&api_key=" + API_KEY + "&sort_by=popularity.desc&limit=10";
        ResponseEntity<MovieListResponse> response = restTemplate.getForEntity(url, MovieListResponse.class);
        return response.getBody().getResults().stream().limit(10).collect(Collectors.toList());
    }

    public List<MovieDto> getWavveTop5() {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/discover/tv?region=KR&language=ko-KR&with_networks=3357&api_key=" + API_KEY + "&sort_by=popularity.desc&limit=10";
        ResponseEntity<MovieListResponse> response = restTemplate.getForEntity(url, MovieListResponse.class);
        return response.getBody().getResults().stream().limit(10).collect(Collectors.toList());
    }

    public List<MovieDto> getAppleTop5() {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/discover/tv?region=KR&language=ko-KR&with_networks=2552&api_key=" + API_KEY + "&sort_by=popularity.desc&limit=10";
        ResponseEntity<MovieListResponse> response = restTemplate.getForEntity(url, MovieListResponse.class);
        return response.getBody().getResults().stream().limit(10).collect(Collectors.toList());
    }
}