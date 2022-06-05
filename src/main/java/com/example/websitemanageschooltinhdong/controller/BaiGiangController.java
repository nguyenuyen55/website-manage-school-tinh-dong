package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.domain.TinTuc;
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
import java.util.List;
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

    @GetMapping("")
    public ResponseEntity<List<BaiGiang>> getALlBaiGiang() {
        List<BaiGiang> baiGiangs = baiGiangService.getall();
        if (baiGiangs.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(baiGiangs, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<BaiGiang>> getALlBaiGiangByMonHocAndTen(@RequestParam("idmon") String idMon,@RequestParam(value = "idchuong",required = false) Integer idchuong, @RequestParam("name") String name) {
        List<BaiGiang> baiGiangs = baiGiangService.getAllByMonHocAndTenChuongHoc(idchuong,idMon,name);
        if (baiGiangs.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(baiGiangs, HttpStatus.OK);
    }
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
    //    update baiGiang
    @PutMapping("/update")
    public ResponseEntity<BaiGiang> update(@Valid @RequestBody BaiGiangDTO baiGiang){
        return new ResponseEntity<>(baiGiangService.updateBaiGiang(baiGiang),HttpStatus.OK);
    }

//delete
@DeleteMapping("/delete/{id}")
public ResponseEntity<Boolean> deletebaigiang(@PathVariable("id") int id){
    return new ResponseEntity<>(baiGiangService.deleteBaiGiang(id),HttpStatus.OK);
}

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", false, details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
