package com.tachung.server.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class TwitchService {

    private static final String CLIENT_ID = "k9t6iqm360tipg7oc2p1h35rzt3ayh";
    private static final String CLIENT_SECRET = "elgfaui90tpkxqwtmiipzd7ce9x9vs";
    private static final String OAUTH_TOKEN_URL = "https://id.twitch.tv/oauth2/token";
    private static final String TWITCH_TOP_GAMES_URL = "https://api.twitch.tv/helix/games/top?first=11";

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

    public String getTopGames(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Client-ID", CLIENT_ID);
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(TWITCH_TOP_GAMES_URL, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return "Error getting Twitch top games: " + response.getStatusCode();
        }
    }
    public String getTopGamesExcludingJustChatting(String accessToken) {
        String jsonTopGames = getTopGames(accessToken);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(jsonTopGames);
            JsonNode dataNode = rootNode.path("data");
            ArrayNode newArrayNode = mapper.createArrayNode();

            for (JsonNode gameNode : dataNode) {
                String gameName = gameNode.get("name").asText();
                if (!"Just Chatting".equals(gameName)) {
                    newArrayNode.add(gameNode);
                }
            }

            ((ObjectNode) rootNode).put("data", newArrayNode);
            return rootNode.toString();

        } catch (Exception e) {
            // JSON 파싱 또는 수정 중에 예외가 발생한 경우
            return "Error processing Twitch top games: " + e.getMessage();
        }
    }
}
