package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.dto.request.BaiGiangDTO;
import com.example.websitemanageschooltinhdong.exception.ErrorResponse;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.service.BaiGiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/baiGiang")
public class BaiGiangController {
/*
* tạo bài giảng cong cong load video lên
* lưu đường dẫn trong db
* get bai giang boi id
*
* */
    @Autowired
    BaiGiangService baiGiangService;
    @GetMapping("{id}")
    public ResponseEntity<BaiGiang> getBaiGiang(@PathVariable("id") int id){
        if(baiGiangService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(baiGiangService.findById(id),HttpStatus.OK);
    }

//    Create baiGiang
@PostMapping("/create")
    public ResponseEntity<BaiGiang> create(@Valid @RequestBody BaiGiangDTO baiGiang){
        return new ResponseEntity<>(baiGiangService.createBaiGiang(baiGiang),HttpStatus.OK);
}
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", false, details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
