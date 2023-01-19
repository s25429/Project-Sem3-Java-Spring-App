package com.test;

import com.test.repositories.*;
import com.test.tables.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DatabaseManagement {
    public enum Tables { AUTHOR, BOOK, COLLECTION, EPOCH, GENRE, KIND, THEME }

    @Autowired
    private static AuthorRepository authorRepository;

    @Autowired
    private static BookRepository bookRepository;

    @Autowired
    private static CollectionRepository collectionRepository;

    @Autowired
    private static EpochRepository epochRepository;

    @Autowired
    private static GenreRepository genreRepository;

    @Autowired
    private static KindRepository kindRepository;

    @Autowired
    private static ThemeRepository themeRepository;

    /*
    @Autowired
    public final static Map<Tables, JpaRepository> tables = Map.ofEntries(
        Map.entry(Tables.AUTHOR, authorRepository),
        Map.entry(Tables.BOOK, bookRepository),
        Map.entry(Tables.COLLECTION, collectionRepository),
        Map.entry(Tables.EPOCH, epochRepository),
        Map.entry(Tables.GENRE, genreRepository),
        Map.entry(Tables.KIND, kindRepository),
        Map.entry(Tables.THEME, themeRepository)
    );

    @Bean
    public Map<Tables, JpaRepository> tables() {
        return Map.ofEntries(
                Map.entry(Tables.AUTHOR, authorRepository),
                Map.entry(Tables.BOOK, bookRepository),
                Map.entry(Tables.COLLECTION, collectionRepository),
                Map.entry(Tables.EPOCH, epochRepository),
                Map.entry(Tables.GENRE, genreRepository),
                Map.entry(Tables.KIND, kindRepository),
                Map.entry(Tables.THEME, themeRepository)
        );
    }
    */
}
