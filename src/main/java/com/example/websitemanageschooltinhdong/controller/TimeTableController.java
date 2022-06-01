package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.ChiTietThoiKhoaBieu;
import com.example.websitemanageschooltinhdong.domain.Lop;
import com.example.websitemanageschooltinhdong.dto.request.TimeTableDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.ChiTibetBleuRepository;
import com.example.websitemanageschooltinhdong.repository.LopRepository;
import com.example.websitemanageschooltinhdong.service.ChiTietBieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TimeTableController {
    @Autowired
    ChiTietBieuService chiTietBieuService;
    @Autowired
    LopRepository lopRepository;

    @Autowired
    ChiTibetBleuRepository chiTietBieuRespository;

    @GetMapping("/tb")
    public ResponseEntity<List<ChiTietThoiKhoaBieu>> getTimeTables(@RequestParam("lop") int idLop){
        Optional<Lop> lop = lopRepository.findById(idLop);
        if (!lop.isPresent()) {
            throw new RecordNotFoundException("lớp này không tồn tại");
        }
//    List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieus = chiTietBieuService.findAllByIdLop(idLop);
        if(chiTietBieuRespository.findAllByIdLop(idLop).size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(chiTietBieuRespository.findAllByIdLop(idLop),HttpStatus.OK);
    }
    @PostMapping("/tb/update")
    public ResponseEntity<List<ChiTietThoiKhoaBieu>> updateTimeTable(@RequestBody List<TimeTableDTO> timeTableDTOS){
        return new ResponseEntity<>(chiTietBieuService.updateTimeTable(timeTableDTOS),HttpStatus.OK);
    }
    @PutMapping("/tb/create/{idlop}")
    public ResponseEntity<List<ChiTietThoiKhoaBieu>> createTimeTable(@RequestBody List<TimeTableDTO> timeTableDTOS,@PathVariable("idlop")int idlop){
        return new ResponseEntity<>(chiTietBieuService.createTimeTable(timeTableDTOS,idlop),HttpStatus.OK);
    }



}
