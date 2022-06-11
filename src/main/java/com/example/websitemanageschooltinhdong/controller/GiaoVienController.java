package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.dto.request.GiaoVienDTO;
import com.example.websitemanageschooltinhdong.exception.ErrorResponse;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.GiaoVienRepository;
import com.example.websitemanageschooltinhdong.service.GiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teacher")
public class GiaoVienController {
    @Autowired
    GiaoVienRepository giaoVienRepository;
    @Autowired
    GiaoVienService giaoVienService;
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

    //crud teacher
@PostMapping("/create")
public ResponseEntity<GiaoVien> createGiaoVien(@RequestBody GiaoVienDTO giaoVienDTO){
        return new ResponseEntity<>(giaoVienService.create(giaoVienDTO),HttpStatus.OK);
}

    //update
    @PutMapping("/update")
    public ResponseEntity<GiaoVien> updateGiaoVien(@RequestBody GiaoVienDTO giaoVienDTO){
        return new ResponseEntity<>(giaoVienService.update(giaoVienDTO),HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteGiaoVien(@PathVariable("id") String id){
        return new ResponseEntity<>(giaoVienService.delete(id),HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<GiaoVien>> findAll(){
        return new ResponseEntity<>(giaoVienRepository.findAll(),HttpStatus.OK);
    }
    //detail
    @GetMapping("/detail/{id}")
    public ResponseEntity<GiaoVien> detailGiaoVien(@PathVariable("id") String id){
        return new ResponseEntity<>(giaoVienService.detail(id),HttpStatus.OK);
    }


    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", false, details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
