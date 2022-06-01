package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.MonHoc;
import com.example.websitemanageschooltinhdong.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monhoc")
public class MonHocController {
    @Autowired
    MonHocRepository monHocRepository;

    @GetMapping("/{khoi}")
public ResponseEntity<List<MonHoc>> getAllByIdKhoi(@PathVariable("khoi") int idKhoi){
        return new  ResponseEntity<>(monHocRepository.findAllByKhoiId(idKhoi), HttpStatus.OK);
    }
}
