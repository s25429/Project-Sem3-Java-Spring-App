package com.jaz.lektury.controllers;

import com.jaz.lektury.components.Database;
import com.jaz.lektury.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ManageDatabaseView {
    @Autowired
    private Database db;


    @GetMapping(value = {"/api/lektury", "/api/lektury/list", "/api/lektury/wszystkie", "/api/lektury/lista"})
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(db.getBookRepository().findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/api/lektury/{id}", "/api/lektury/get/{id}", "/api/lektury/pokaz/{id}"})
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return db.getBookRepository().findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = {"/api/lektury", "/api/lektury/add", "/api/lektury/dodaj"})
    public ResponseEntity<Long> postBook(@RequestBody Book body) {
        if (body.id != null && db.getBookRepository().existsById(body.id))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

        var saved = db.getBookRepository().save(body);
        return new ResponseEntity<>(saved.id, HttpStatus.CREATED);
    }

    @PutMapping(value = {"/api/lektury/{id}", "/api/lektury/update/{id}", "/api/lektury/zaktualizuj/{id}"})
    public ResponseEntity<Long> putBook(@RequestBody Book body, @PathVariable Long id) {
        return db.getBookRepository().findById(id)
                .map(value -> {
                    // TODO relations could have ids of authors not present in db
                    value.overwrite(body);
                    var saved = db.getBookRepository().save(value);
                    return new ResponseEntity<>(saved.id, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = {"/api/lektury/{id}", "/api/lektury/delete/{id}", "/api/lektury/usun/{id}"})
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return db.getBookRepository().findById(id)
                .map(value -> {
                    db.getBookRepository().deleteById(value.id);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = {"/api/autorzy", "/api/autorzy/list", "/api/autorzy/wszystkie", "/api/autorzy/lista"})
    public ResponseEntity<List<Author>> getAuthors() {
        return new ResponseEntity<>(db.getAuthorRepository().findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/api/autorzy/{id}", "/api/autorzy/get/{id}", "/api/autorzy/pokaz/{id}"})
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        return db.getAuthorRepository().findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = {"/api/autorzy", "/api/autorzy/add", "/api/autorzy/dodaj"})
    public ResponseEntity<Long> postAuthor(@RequestBody Author body) {
        if (body.id != null && db.getAuthorRepository().existsById(body.id))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

        var saved = db.getAuthorRepository().save(body);
        return new ResponseEntity<>(saved.id, HttpStatus.CREATED);
    }

    @PutMapping(value = {"/api/autorzy/{id}", "/api/autorzy/update/{id}", "/api/autorzy/zaktualizuj/{id}"})
    public ResponseEntity<Long> putAuthor(@RequestBody Author body, @PathVariable Long id) {
        return db.getAuthorRepository().findById(id)
                .map(value -> {
                    value.overwrite(body);
                    var saved = db.getAuthorRepository().save(value);
                    return new ResponseEntity<>(saved.id, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = {"/api/autorzy/{id}", "/api/autorzy/delete/{id}", "/api/autorzy/usun/{id}"})
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        return db.getAuthorRepository().findById(id)
                .map(value -> {
                    db.getAuthorRepository().deleteById(value.id);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = {"/api/epoki", "/api/epoki/list", "/api/epoki/wszystkie", "/api/epoki/lista"})
    public ResponseEntity<List<Epoch>> getEpochs() {
        return new ResponseEntity<>(db.getEpochRepository().findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/api/epoki/{id}", "/api/epoki/get/{id}", "/api/epoki/pokaz/{id}"})
    public ResponseEntity<Epoch> getEpoch(@PathVariable Long id) {
        return db.getEpochRepository().findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = {"/api/epoki", "/api/epoki/add", "/api/epoki/dodaj"})
    public ResponseEntity<Long> postEpoki(@RequestBody Epoch body) {
        if (body.id != null && db.getEpochRepository().existsById(body.id))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

        var saved = db.getEpochRepository().save(body);
        return new ResponseEntity<>(saved.id, HttpStatus.CREATED);
    }

    @PutMapping(value = {"/api/epoki/{id}", "/api/epoki/update/{id}", "/api/epoki/zaktualizuj/{id}"})
    public ResponseEntity<Long> putEpoki(@RequestBody Epoch body, @PathVariable Long id) {
        return db.getEpochRepository().findById(id)
                .map(value -> {
                    value.overwrite(body);
                    var saved = db.getEpochRepository().save(value);
                    return new ResponseEntity<>(saved.id, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = {"/api/epoki/{id}", "/api/epoki/delete/{id}", "/api/epoki/usun/{id}"})
    public ResponseEntity<?> deleteEpoki(@PathVariable Long id) {
        return db.getEpochRepository().findById(id)
                .map(value -> {
                    db.getEpochRepository().deleteById(value.id);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = {"/api/gatunki", "/api/gatunki/list", "/api/gatunki/wszystkie", "/api/gatunki/lista"})
    public ResponseEntity<List<Genre>> getGenres() {
        return new ResponseEntity<>(db.getGenreRepository().findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/api/gatunki/{id}", "/api/gatunki/get/{id}", "/api/gatunki/pokaz/{id}"})
    public ResponseEntity<Genre> getGenre(@PathVariable Long id) {
        return db.getGenreRepository().findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = {"/api/gatunki", "/api/gatunki/add", "/api/gatunki/dodaj"})
    public ResponseEntity<Long> postGenre(@RequestBody Genre body) {
        if (body.id != null && db.getGenreRepository().existsById(body.id))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

        var saved = db.getGenreRepository().save(body);
        return new ResponseEntity<>(saved.id, HttpStatus.CREATED);
    }

    @PutMapping(value = {"/api/gatunki/{id}", "/api/gatunki/update/{id}", "/api/gatunki/zaktualizuj/{id}"})
    public ResponseEntity<Long> putGenre(@RequestBody Genre body, @PathVariable Long id) {
        return db.getGenreRepository().findById(id)
                .map(value -> {
                    value.overwrite(body);
                    var saved = db.getGenreRepository().save(value);
                    return new ResponseEntity<>(saved.id, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = {"/api/rodzaje", "/api/rodzaje/list", "/api/rodzaje/wszystkie", "/api/rodzaje/lista"})
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        return db.getGenreRepository().findById(id)
                .map(value -> {
                    db.getGenreRepository().deleteById(value.id);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = {"/api/rodzaje", "/api/rodzaje/list", "/api/rodzaje/wszystkie", "/api/rodzaje/lista"})
    public ResponseEntity<List<Kind>> getKinds() {
        return new ResponseEntity<>(db.getKindRepository().findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/api/rodzaje/{id}", "/api/rodzaje/get/{id}", "/api/rodzaje/pokaz/{id}"})
    public ResponseEntity<Kind> getKind(@PathVariable Long id) {
        return db.getKindRepository().findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = {"/api/rodzaje", "/api/rodzaje/add", "/api/rodzaje/dodaj"})
    public ResponseEntity<Long> postKind(@RequestBody Kind body) {
        if (body.id != null && db.getKindRepository().existsById(body.id))
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

        var saved = db.getKindRepository().save(body);
        return new ResponseEntity<>(saved.id, HttpStatus.CREATED);
    }

    @PutMapping(value = {"/api/rodzaje/{id}", "/api/rodzaje/update/{id}", "/api/rodzaje/zaktualizuj/{id}"})
    public ResponseEntity<Long> putKind(@RequestBody Kind body, @PathVariable Long id) {
        return db.getKindRepository().findById(id)
                .map(value -> {
                    value.overwrite(body);
                    var saved = db.getKindRepository().save(value);
                    return new ResponseEntity<>(saved.id, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = {"/api/rodzaje/{id}", "/api/rodzaje/delete/{id}", "/api/rodzaje/usun/{id}"})
    public ResponseEntity<?> deleteKind(@PathVariable Long id) {
        return db.getKindRepository().findById(id)
                .map(value -> {
                    db.getKindRepository().deleteById(value.id);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
