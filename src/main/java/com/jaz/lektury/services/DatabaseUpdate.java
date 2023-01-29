package com.jaz.lektury.services;

import com.jaz.lektury.components.Database;
import com.jaz.lektury.dtos.*;
import com.jaz.lektury.tables.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;


@Service
public class DatabaseUpdate {
    private final boolean DEBUG = false;
    private final String API_URL = "https://wolnelektury.pl/api/";

    private final Logger logger = LogManager.getLogger(DatabaseUpdate.class);
    private final StopWatch timer = new StopWatch();
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Database db;


    public void update(boolean cleanUpdate) {
        updateWithLimits(cleanUpdate, -1, -1, -1, -1, -1);
    }

    public void debugUpdate(boolean cleanUpdate) {
        updateWithLimits(cleanUpdate, 300, 200, -1, -1, -1);
    }

    public void updateWithLimits(boolean cleanUpdate, long alimit, long blimit, long elimit, long glimit, long klimit) {
        // Takes around 7.3 minutes with no limit restrictions

        // Make maps of relations for faster lookup when assigning
        Map<String, Author> authorMap = new HashMap<>();
        Map<String, Epoch> epochMap = new HashMap<>();
        Map<String, Genre> genreMap = new HashMap<>();
        Map<String, Kind> kindMap = new HashMap<>();

        // Clean database from any existing records
        if (cleanUpdate) cleanupDatabase();

        // Process authors
        Stream<Author> authorStream = requestAPI("authors", AuthorDto[].class, alimit)
                .map(AuthorDto::toEntity);
        insertRecords("authors", authorStream, author -> {
            db.getAuthorRepository().save(author);
            authorMap.put(author.fullname, author);
        });

        // Process epochs
        Stream<Epoch> epochStream = requestAPI("epochs", EpochDto[].class, elimit)
                .map(EpochDto::toEntity);
        insertRecords("epochs", epochStream, epoch -> {
            db.getEpochRepository().save(epoch);
            epochMap.put(epoch.name, epoch);
        });

        // Process genres
        Stream<Genre> genreStream = requestAPI("genres", GenreDto[].class, glimit)
                .map(GenreDto::toEntity);
        insertRecords("genres", genreStream, genre -> {
            db.getGenreRepository().save(genre);
            genreMap.put(genre.name, genre);
        });

        // Process kinds
        Stream<Kind> kindStream = requestAPI("kinds", KindDto[].class, klimit)
                .map(KindDto::toEntity);
        insertRecords("kinds", kindStream, kind -> {
            db.getKindRepository().save(kind);
            kindMap.put(kind.name, kind);
        });

        // Process books
        Stream<Book> bookStream = requestAPI("books", BookDto[].class, blimit)
                .map(bookDto -> mapBooksWithRelations(bookDto, authorMap, epochMap, genreMap, kindMap));
        insertRecords("books", bookStream, db.getBookRepository()::save);
    }

    private void cleanupDatabase() {
        db.getBookRepository().deleteAll();
        db.getAuthorRepository().deleteAll();
        db.getEpochRepository().deleteAll();
        db.getGenreRepository().deleteAll();
        db.getKindRepository().deleteAll();
    }

    private <DTO> Stream<DTO> requestAPI(String uri, Class<DTO[]> returnClassListType, long limit) {
        try {
            timer.start();
            DTO[] dtoArray = Objects.requireNonNull(restTemplate.getForObject(API_URL + uri, returnClassListType));
            timer.stop();

            Stream<DTO> dtoStream = limit > 0 ? Arrays.stream(dtoArray).limit(limit) : Arrays.stream(dtoArray);

            logger.info(
                    "[{}] Got {} record(s) in {}ms, using {} record(s)",
                    () -> uri,
                    () -> dtoArray.length,
                    timer::getLastTaskTimeMillis,
                    () -> limit > 0 ? limit : dtoArray.length
            );

            return dtoStream;
        } catch (HttpClientErrorException e) {
            if (timer.isRunning()) timer.stop();
            if (DEBUG) e.printStackTrace();
            logger.error(
                    "Error {}, could not retrieve data from {}",
                    e.getStatusCode()::value,
                    () -> API_URL + uri
            );
            return Stream.<DTO>builder().build();
        }
    }

    private Book mapBooksWithRelations(BookDto bookDto, Map<String, Author> author, Map<String, Epoch> epoch, Map<String, Genre> genre, Map<String, Kind> kind) {
        Book book = bookDto.toEntity();
        book.author = author.get(bookDto.author);
        book.epoch = epoch.get(bookDto.epoch);
        book.genre = genre.get(bookDto.genre);
        book.kind = kind.get(bookDto.kind);
        return book;
    }

    private <T> void insertRecords(String uri, Stream<T> stream, Consumer<T> callback) {
        timer.start();
        stream.forEach(callback);
        timer.stop();

        logger.info(
                "[{}] Finished inserting records in {}ms",
                () -> uri,
                timer::getLastTaskTimeMillis
        );
    }
}
