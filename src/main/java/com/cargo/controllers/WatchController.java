package com.cargo.controllers;

import com.cargo.services.WatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Api(value = "Cargo controller", description = "Operations pertaining to ...")
@AllArgsConstructor
public class WatchController {

    private final WatchService watchService;

    @ApiOperation(value = "View a list of watches")
    @PostMapping(path ="/watches",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getWatch(@RequestBody final List<Integer> watchesIds) {
        watchService.getValue(watchesIds);
        return ResponseEntity.status(HttpStatus.OK)
            .body(2.14);
    }

    /* swagger not support get with body request */
    @ApiOperation(value = "View a list of watches")
    @GetMapping("/watches")
    public ResponseEntity<String> getTest(@RequestBody final List<Integer> watchesIds) {
        return ResponseEntity.status(HttpStatus.OK)
            .body("test");
    }

}
