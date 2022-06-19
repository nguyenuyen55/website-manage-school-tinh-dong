package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.TinTuc;
import com.example.websitemanageschooltinhdong.dto.request.TinTucDTO;
import com.example.websitemanageschooltinhdong.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //create
    @PostMapping("/create")
    public ResponseEntity<TinTuc> create(@RequestBody TinTucDTO tinTucDTO) {
        return new ResponseEntity<>(tinTucService.create(tinTucDTO), HttpStatus.OK);
    }
    //update
    @PutMapping("/update")
    public ResponseEntity<TinTuc> update(@RequestBody TinTucDTO tinTuc) {
        return new ResponseEntity<>(tinTucService.update(tinTuc), HttpStatus.OK);
    }
    //update
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        return new ResponseEntity<>(tinTucService.delete(id), HttpStatus.OK);
    }
}