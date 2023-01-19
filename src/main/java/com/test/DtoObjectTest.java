package com.test;

import com.test.dtos.*;
import com.test.tables.Kind;
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
    private Database db;


    @GetMapping("/test")
    public ResponseEntity<String> testRequest() {
        getAuthors(2);
        getBooks(2);
        getCollections(2);
        getEpochs(2);
        getGenres(2);
        getKinds(2);
        getThemes(2);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private <T> T requestAPI(String url, Class<T> tClass) {
        return Objects.requireNonNull(restTemplate.getForObject(url, tClass));
    }

    private void getAuthors() {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/authors", AuthorDto[].class))
                .map(AuthorDto::toEntity)
                .forEach(x -> db.table(Database.Table.AUTHOR).save(x));
    }

    private void getAuthors(long limit) {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/authors", AuthorDto[].class))
                .limit(limit)
                .map(AuthorDto::toEntity)
                .forEach(x -> db.table(Database.Table.AUTHOR).save(x));
    }

    private void getBooks() {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/books", BookDto[].class))
                .map(BookDto::toEntity)
                .forEach(x -> db.table(Database.Table.BOOK).save(x));
    }

    private void getBooks(long limit) {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/books", BookDto[].class))
                .limit(limit)
                .map(BookDto::toEntity)
                .forEach(x -> db.table(Database.Table.BOOK).save(x));
    }

    private void getCollections() {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/collections", CollectionDto[].class))
                .map(CollectionDto::toEntity)
                .forEach(x -> db.table(Database.Table.COLLECTION).save(x));
    }

    private void getCollections(long limit) {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/collections", CollectionDto[].class))
                .limit(limit)
                .map(CollectionDto::toEntity)
                .forEach(x -> db.table(Database.Table.COLLECTION).save(x));
    }

    private void getEpochs() {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/epochs", EpochDto[].class))
                .map(EpochDto::toEntity)
                .forEach(x -> db.table(Database.Table.EPOCH).save(x));
    }

    private void getEpochs(long limit) {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/epochs", EpochDto[].class))
                .limit(limit)
                .map(EpochDto::toEntity)
                .forEach(x -> db.table(Database.Table.EPOCH).save(x));
    }

    private void getGenres() {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/genres", GenreDto[].class))
                .map(GenreDto::toEntity)
                .forEach(x -> db.table(Database.Table.GENRE).save(x));
    }

    private void getGenres(long limit) {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/genres", GenreDto[].class))
                .limit(limit)
                .map(GenreDto::toEntity)
                .forEach(x -> db.table(Database.Table.GENRE).save(x));
    }

    private void getKinds() {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/kinds", KindDto[].class))
                .map(KindDto::toEntity)
                .forEach(x -> db.table(Database.Table.KIND).save(x));
    }

    private void getKinds(long limit) {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/kinds", KindDto[].class))
                .limit(limit)
                .map(KindDto::toEntity)
                .forEach(x -> db.table(Database.Table.KIND).save(x));
    }

    private void getThemes() {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/themes", ThemeDto[].class))
                .map(ThemeDto::toEntity)
                .forEach(x -> db.table(Database.Table.THEME).save(x));
    }

    private void getThemes(long limit) {
        Arrays.stream(requestAPI("https://wolnelektury.pl/api/themes", ThemeDto[].class))
                .limit(limit)
                .map(ThemeDto::toEntity)
                .forEach(x -> db.table(Database.Table.THEME).save(x));
    }
}
