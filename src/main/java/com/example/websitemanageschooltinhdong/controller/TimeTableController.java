package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.ChiTietThoiKhoaBieu;
import com.example.websitemanageschooltinhdong.repository.ChiTietBieuRespository;
import com.example.websitemanageschooltinhdong.service.ChiTietBieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TimeTableController {
    @Autowired
    ChiTietBieuService chiTietBieuService;

    @Autowired
    ChiTietBieuRespository chiTietBieuRespository;

    @GetMapping("/tb")
    public ResponseEntity<List<ChiTietThoiKhoaBieu>> getTimeTables(@RequestParam("lop") int idLop){

//    List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieus = chiTietBieuService.findAllByIdLop(idLop);
        return new ResponseEntity<>(chiTietBieuRespository.findAllByThoiKhoaBieuLop_Id(idLop),HttpStatus.OK);
    }

}
