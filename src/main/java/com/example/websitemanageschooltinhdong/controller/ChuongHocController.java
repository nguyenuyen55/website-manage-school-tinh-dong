package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.domain.ChuongHoc;
import com.example.websitemanageschooltinhdong.repository.ChuongHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chuonghoc")
public class ChuongHocController {
    @Autowired
    ChuongHocRepository baiGianHocRepository;
    @GetMapping("{idmon}")
    public ResponseEntity<List<ChuongHoc>> getall(@PathVariable("idmon") String id ) {
        List<ChuongHoc> chuongHocs = baiGianHocRepository.findAllByMonHoc_Id(id);
        if (chuongHocs.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chuongHocs, HttpStatus.OK);
    }
}
