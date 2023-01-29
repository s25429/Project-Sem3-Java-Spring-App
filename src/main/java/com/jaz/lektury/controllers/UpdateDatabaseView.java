package com.jaz.lektury.controllers;

import com.jaz.lektury.services.DatabaseUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UpdateDatabaseView {
    @Autowired
    private DatabaseUpdate db;


    @GetMapping("/db/update")
    public ResponseEntity<String> updateDatabase() {
        // Add records from API
        db.update(false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/db/clean-update")
    public ResponseEntity<String> cleanUpdateDatabase() {
        // Clear database and then add records from API
        db.update(true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/db/debug-update")
    public ResponseEntity<String> debugCleanUpdateDatabase() {
        // Clear database and add limited number of records from API
        db.debugUpdate(true);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
