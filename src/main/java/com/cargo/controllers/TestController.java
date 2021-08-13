package com.cargo.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(value = "Template controler")
public class TestController {

    @ApiOperation(value = "test only")
    @PostMapping(path ="/test")
    public ResponseEntity<String> test(/* final @RequestBody CreditAss cs */){
        return new ResponseEntity("ff", HttpStatus.OK);

    }
}
