package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.domain.Khoi;
import com.example.websitemanageschooltinhdong.repository.KhoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/khoi")
public class KhoiController {
    @Autowired
    KhoiRepository khoiRepository;
    @GetMapping("")
    public ResponseEntity<List<Khoi>> listResponseEntity(){
        List<Khoi> khois = khoiRepository.findAll();
        if (khois.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(khois, HttpStatus.OK);
    }
}
