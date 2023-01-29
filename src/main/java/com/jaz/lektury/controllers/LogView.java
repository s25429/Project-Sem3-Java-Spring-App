package com.jaz.lektury.controllers;

import com.jaz.lektury.components.LogReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class LogView {
    @Autowired
    private LogReader logReader;

    @GetMapping("/logs/read") // e.x. /logs/read?level=info
    public ResponseEntity<String> readLogs(@RequestParam Optional<String> level) {
        return new ResponseEntity<>(logReader.readLogs(level), HttpStatus.OK);
    }
}
