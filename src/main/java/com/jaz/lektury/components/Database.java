package com.jaz.lektury.components;

import com.jaz.lektury.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Database {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EpochRepository epochRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private KindRepository kindRepository;


    public AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public EpochRepository getEpochRepository() {
        return epochRepository;
    }

    public GenreRepository getGenreRepository() {
        return genreRepository;
    }

    public KindRepository getKindRepository() {
        return kindRepository;
    }
}
