package com.tachung.server.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PersonListResponse {
    private List<Person> results;
}
