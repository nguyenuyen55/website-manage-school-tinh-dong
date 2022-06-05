package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.CauHoi;
import com.example.websitemanageschooltinhdong.domain.ChuDe;
import com.example.websitemanageschooltinhdong.dto.request.CauHoiDTO;
import com.example.websitemanageschooltinhdong.repository.ChuDeRepository;
import com.example.websitemanageschooltinhdong.service.CauHoiService;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
@Autowired
    CauHoiService cauHoiService;
    @Autowired
    ChuDeRepository chuDeRepository;
    @PostMapping("create")
    public ResponseEntity<CauHoi> createCauHoi(@RequestBody CauHoiDTO cauHoi) {
        return new ResponseEntity<>(cauHoiService.createCauHoi(cauHoi), HttpStatus.OK);
    }

@GetMapping("/chude")
    public ResponseEntity<List<ChuDe>> listResponseEntity(){
    return new ResponseEntity<>(chuDeRepository.findAll(), HttpStatus.OK);

}
}
