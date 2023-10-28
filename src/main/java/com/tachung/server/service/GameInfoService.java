package com.tachung.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tachung.server.DTO.GameInfo;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GameInfoService {

    private static final String CLIENT_ID = "k9t6iqm360tipg7oc2p1h35rzt3ayh";
    private static final String CLIENT_SECRET = "elgfaui90tpkxqwtmiipzd7ce9x9vs";
    private static final String OAUTH_TOKEN_URL = "https://id.twitch.tv/oauth2/token";
    private static final String TWITCH_TOP_GAMES_URL = "https://https://api.igdb.com/v4/games?fields=name,release_dates.date,aggregated_rating&sort=aggregated_rating&order=desc&limit=10";
    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        String url = OAUTH_TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&grant_type=client_credentials";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            if (body != null) {
                int startIndex = body.indexOf("\"access_token\":\"") + 16;
                int endIndex = body.indexOf("\"", startIndex);
                return body.substring(startIndex, endIndex);
            }
        }
        return null;
    }
    public List<GameInfo> getTopGames(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Client-ID", CLIENT_ID);
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(TWITCH_TOP_GAMES_URL, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<GameInfo> topGames = new ArrayList<>();
        try {
            topGames = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructCollectionType(List.class, GameInfo.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return topGames;
    }
}