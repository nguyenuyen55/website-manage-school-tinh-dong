package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.PhongBan;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/phongbans")
public class PhongBanController {
    @Autowired
    PhongBanRepository phongBanRepository;

    @GetMapping()
    public ResponseEntity<List<PhongBan>> listEntityResponse() {
        if (phongBanRepository.findAll().size() == 0) {
            throw new RecordNotFoundException("Không tìm thấy phòng ban nào");
        }
        return new ResponseEntity<>(phongBanRepository.findAll(), HttpStatus.OK);
    }
}
