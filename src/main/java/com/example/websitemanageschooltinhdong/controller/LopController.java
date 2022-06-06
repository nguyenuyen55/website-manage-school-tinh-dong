package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.Lop;
import com.example.websitemanageschooltinhdong.dto.request.GiaoVienLopDTO;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhLopDTO;
import com.example.websitemanageschooltinhdong.dto.request.LopDTO;
import com.example.websitemanageschooltinhdong.dto.response.LopGiaoVienResponse;
import com.example.websitemanageschooltinhdong.exception.ErrorResponse;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.service.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lop")
public class LopController {
    @Autowired
    LopService lopService;

    @PostMapping("/create")
    public ResponseEntity<Lop> createLop(@RequestBody LopDTO lopDTO) {
        //xet nam hoc nua
        return new ResponseEntity<>(lopService.create(lopDTO), HttpStatus.OK);
    }

    // lisst tat ca cac lop
    @GetMapping("/list")
    public ResponseEntity<List<LopGiaoVienResponse>> listLop() {
        return new ResponseEntity<>(lopService.findAllLop(), HttpStatus.OK);
    }

    //xem danh sach lop boi id
    @GetMapping("/list/{id}")
    public ResponseEntity<Lop> getLop(@PathVariable("id") int id) {
        return new ResponseEntity<>(lopService.detail(id), HttpStatus.OK);
    }
    //xem danh sach lop boi id
    @GetMapping("/khoi/{idkhoi}")
    public ResponseEntity<List<Lop>> getLopByIdKhoi(@PathVariable("idkhoi") int id) {
        return new ResponseEntity<>(lopService.findAllByBlock(id), HttpStatus.OK);
    }

    //chinh sua giao vien
    @PostMapping("/updatelop")
    public ResponseEntity<Boolean> updategiaovien(@RequestBody GiaoVienLopDTO giaoVienLopDTO) {
        return new ResponseEntity<>(lopService.updadegvlop(giaoVienLopDTO), HttpStatus.OK);
    }


    //len lop
// cắt chuỗi cho nó lên 5
    //tìm lớp tiếp theo 1A xong cátc chuỗi nếu 1 thì tim 2 +a

    @PostMapping("/updateLopHocSinh")
    public ResponseEntity<Boolean> updatehocsinhinlop(@RequestBody HocSinhLopDTO hocSinhLop) {
        return new ResponseEntity<>(lopService.updateLenLop(hocSinhLop.getTen(), hocSinhLop.getYear(), hocSinhLop.getIdgv(), hocSinhLop.getIdlop()), HttpStatus.OK
        );
    }
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", false, details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

}
