package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.domain.Khoi;
import com.example.websitemanageschooltinhdong.dto.response.KhoiDTO;
import com.example.websitemanageschooltinhdong.repository.KhoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/khoi")
public class KhoiController {
    @Autowired
    KhoiRepository khoiRepository;
    @GetMapping("")
    public ResponseEntity<List<KhoiDTO>> listResponseEntity(){
        List<KhoiDTO> khoiDTOSs = new ArrayList<>();
        List<Khoi> khois = khoiRepository.findAll();
        if (khois.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for(Khoi khoi : khois){
            KhoiDTO  khoiDTO = new KhoiDTO();
            khoiDTO.setId(khoi.getId());
            khoiDTO.setTen(khoi.getTen());
                 khoiDTOSs.add(khoiDTO);
        }
        return new ResponseEntity<>(khoiDTOSs, HttpStatus.OK);
    }
}
