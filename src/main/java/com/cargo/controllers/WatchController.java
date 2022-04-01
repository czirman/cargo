package com.cargo.controllers;

import com.cargo.services.WatchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class WatchController {

    @Autowired
    public Environment env;

    private final WatchService watchService;

    @PostMapping(path = "/watches",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getWatch(@RequestBody final List<Integer> watchesIds) {
        watchService.getValue(watchesIds);
        return ResponseEntity.status(HttpStatus.OK)
                .body(2.14);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest(/*@RequestBody final List<Integer> watchesIds*/) {
        return ResponseEntity.status(HttpStatus.OK)
                .body("test");
    }

}
