package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.DiemMonHoc;
import com.example.websitemanageschooltinhdong.dto.request.DiemHocSinhDTO;
import com.example.websitemanageschooltinhdong.service.DiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diem")
public class DiemController {

    /*
     * get diem từng hoc sinh
     *
     * */
    @Autowired
    DiemService diemService;

    @GetMapping("/{idHs}")
    public ResponseEntity<List<DiemMonHoc>> getDiemHocSinhById(@PathVariable("idHs") String id) {
        return new ResponseEntity<>(diemService.getDiemByHocSinh(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DiemMonHoc>> getDiemHocSinhByIdAndNamHoc(@RequestParam("namhoc") int idNamhoc, @RequestParam("hocsinh") String id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName= authentication.getName();
        return new ResponseEntity<>(diemService.getDiemByHocSinhAndNamHoc(id, idNamhoc), HttpStatus.OK);
    }

    //cap nhat diem cho tung hoc sinh
///b1 get list học sinh boi id giao vien
    //b2 update diem cho tung dua

    @PutMapping("hocSinh")
    public ResponseEntity<DiemMonHoc> updateDiemHocSinh(@RequestBody DiemHocSinhDTO diemHocSinhDTO) {
        return new ResponseEntity<>(diemService.updateDiemByHocSinh(diemHocSinhDTO), HttpStatus.OK);
    }

}
