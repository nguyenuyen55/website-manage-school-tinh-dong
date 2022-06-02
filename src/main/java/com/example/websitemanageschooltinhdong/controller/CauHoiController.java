package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.CauHoi;
import com.example.websitemanageschooltinhdong.repository.CauHoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cauhoi")
public class CauHoiController {
    @Autowired
    CauHoiRepository cauHoiRepository;
    @GetMapping("list")
    public ResponseEntity<List<CauHoi>> getAllQuestion() {
        if(cauHoiRepository.findAll().size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cauHoiRepository.findAll(), HttpStatus.OK);
    }

}
