package com.test;

import com.test.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class Database {
    public enum Table { AUTHOR, BOOK, COLLECTION, EPOCH, GENRE, KIND, THEME }

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private EpochRepository epochRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private KindRepository kindRepository;

    @Autowired
    private ThemeRepository themeRepository;


    public JpaRepository table(Table table) {
        return switch (table) {
            case AUTHOR -> authorRepository;
            case BOOK -> bookRepository;
            case COLLECTION -> collectionRepository;
            case EPOCH -> epochRepository;
            case GENRE -> genreRepository;
            case KIND -> kindRepository;
            case THEME -> themeRepository;
        };
    }

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
