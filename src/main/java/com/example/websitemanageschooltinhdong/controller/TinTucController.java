package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.TinTuc;
import com.example.websitemanageschooltinhdong.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/listNew")
public class TinTucController {

    @Autowired
    TinTucService tinTucService;

    @GetMapping("")
    public ResponseEntity<List<TinTuc>> getListTinTuc() {
        List<TinTuc> tinTucs = tinTucService.getAllTinTuc();
        if (tinTucs.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tinTucs, HttpStatus.OK);
    }

    //get chi tiet
    @GetMapping("/{id}")
    public ResponseEntity<TinTuc> getTinTucById(@PathVariable("id") int id) {
        TinTuc tinTuc = tinTucService.getTinTucById(id);
        if (tinTuc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tinTuc, HttpStatus.OK);
    }

}