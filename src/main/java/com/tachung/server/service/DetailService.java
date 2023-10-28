package com.tachung.server.service;

import com.tachung.server.DTO.PersonDetailDTO;
import com.tachung.server.DTO.ottDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DetailService {
    private final String API_URL = "https://api.themoviedb.org/3";
    private final String API_KEY = "d339d2e4a1c59c8499dacf75c38da31c";

    public ottDetail getMovieDetail(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/tv/" + id + "?api_key=" + API_KEY + "&language=ko-KR";
        ResponseEntity<ottDetail > response = restTemplate.getForEntity(url, ottDetail .class);
        return response.getBody();
    }


    public ottDetail getVideoDetail(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/tv/" + id + "/videos?api_key=" + API_KEY + "&language=ko-KR";
        ResponseEntity<ottDetail > response = restTemplate.getForEntity(url, ottDetail .class);
        return response.getBody();
    }

    public ottDetail getProviersDetail(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "/tv/" + id + "/watch/providers?api_key=" + API_KEY + "&language=ko-KR";
        ResponseEntity<ottDetail > response = restTemplate.getForEntity(url, ottDetail .class);
        return response.getBody();
    }
}
