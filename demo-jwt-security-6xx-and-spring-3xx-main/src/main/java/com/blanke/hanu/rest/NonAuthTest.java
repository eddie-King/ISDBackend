package com.blanke.hanu.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class NonAuthTest {


    @GetMapping("/authenticate")
    public ResponseEntity<String> signUp() {
        return ResponseEntity.ok("okkkkkk mannnnn");
    }

}
