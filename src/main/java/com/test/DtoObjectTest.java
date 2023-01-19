package com.test;

import com.test.dtos.AuthorDto;
import com.test.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@RestController
public class DtoObjectTest {
    public RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public AuthorRepository authorTable;


    @GetMapping("/test")
    public ResponseEntity<String> testRequest() {
        String url = "https://wolnelektury.pl/api/authors";

        Arrays.stream(Objects.requireNonNull(requestAPI(url, AuthorDto[].class)))
                .limit(2)
                .map(AuthorDto::toEntity)
                .forEach(authorTable::save);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private <T> T requestAPI(String url, Class<T> tClass) {
        return restTemplate.getForObject(url, tClass);
    }
}
