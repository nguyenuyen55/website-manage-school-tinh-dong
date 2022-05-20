package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.repository.GiaoVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class GiaoVienController {
    @Autowired
    GiaoVienRepository giaoVienRepository;
    @GetMapping("/search")
    public ResponseEntity<List<GiaoVien>> search(@RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "idBan", required = false) String idBan
                                                 ) {
        List<GiaoVien> giaoViens = new ArrayList<>();
        if(name==null && idBan==null){
            giaoViens= giaoVienRepository.findAll();
        }
        if(name !=null && idBan==null){
            giaoViens= giaoVienRepository.findAllByTenContaining(name);
        }
        if(name !=null && idBan!=null){
            giaoViens= giaoVienRepository.findAllByTenContainingAndPhongBan_Id(name,idBan);
        }
        if(name == null && idBan !=null){
            giaoViens= giaoVienRepository.findAllByPhongBan_Id(idBan);
        }
        if(giaoViens.size()==0){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(giaoViens, HttpStatus.OK);
    }
}
