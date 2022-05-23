package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.CauHoi;
import com.example.websitemanageschooltinhdong.service.CauHoiService;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
@Autowired
    CauHoiService cauHoiService;
    @PostMapping("create")
    public ResponseEntity<CauHoi> createCauHoi(@RequestBody CauHoi cauHoi){
        return new ResponseEntity<>(cauHoiService.createCauHoi(cauHoi), HttpStatus.OK);
    }
}
