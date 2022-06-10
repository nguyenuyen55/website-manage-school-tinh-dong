package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.NamHoc;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.NamHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/namhocs")
public class NamHocController {
    @Autowired
    NamHocRepository namHocRepository;

    @GetMapping("")
    public ResponseEntity<List<NamHoc>> getAllYear(){
        if(namHocRepository.findAll().size()==0){
            throw new RecordNotFoundException("Không tìm thấy năm này");
        }
        return new ResponseEntity<>(namHocRepository.findAll(),HttpStatus.OK);
    }
}
