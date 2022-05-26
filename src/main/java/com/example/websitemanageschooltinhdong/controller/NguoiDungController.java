package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NguoiDungController {
@Autowired
    NguoiDungRepository nguoiDungRepository;
    @GetMapping("/api/nguoiDung/{ten}")
    public ResponseEntity<NguoiDung> getNguoiDung(@PathVariable("ten") String ten){
        return new ResponseEntity<NguoiDung>(nguoiDungRepository.findById(9).get(), HttpStatus.OK);
    }
}
