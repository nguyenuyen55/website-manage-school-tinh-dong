package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhDTO;
import com.example.websitemanageschooltinhdong.exception.ErrorResponse;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.GiaoVienRepository;
import com.example.websitemanageschooltinhdong.service.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.*;

@RestController
@RequestMapping("api/student")
public class HocSinhController {
    @Autowired
    HocSinhService hocSinhService;
    @Autowired
    GiaoVienRepository giaoVienRepository;
    @GetMapping("/search")
    public ResponseEntity<List<HocSinh>> search(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "idClass", required = false) Integer idClass,
                                                @RequestParam(value = "idBlock", required = false) Integer idBlock) {
        List<HocSinh> hocSinhs = new ArrayList<>();
        if (name == null && idClass == null && idBlock == null) {
            hocSinhs = hocSinhService.searchHocSinhAll();
        }
        if (name != null && idClass == null && idBlock == null) {
            hocSinhs = hocSinhService.searchHocSinhByTen(name);
        }
        if (name != null && idClass == null && idBlock != null) {
            hocSinhs = hocSinhService.searchHocSinhByTenKhoi(name, idBlock);
        }
        if (name != null && idClass != null && idBlock != null) {
            hocSinhs = hocSinhService.searchHocSinhByTenKhoiLop(name, idClass, idBlock);
        }
        if (hocSinhs.size()==0 ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hocSinhs, HttpStatus.OK);
    }

    //get List student by id teacher
    @GetMapping("/list/{idTeacher}")
    public ResponseEntity<List<HocSinh>> getListHocSinhByIdTeacher(@PathVariable("idTeacher") String idTeacher){
       int idLop = 0;
        Optional<GiaoVien>  giaoVien = giaoVienRepository.findById(idTeacher);
        if(giaoVien.isPresent()){
            idLop=giaoVien.get().getLopgv().getId();
        }
        return new ResponseEntity<>( hocSinhService.findAllIdLop(idLop),HttpStatus.OK);
    }
//get list
@GetMapping("/list")
public ResponseEntity<List<HocSinh>> getList()
{
    return new ResponseEntity<>(hocSinhService.findALL(),HttpStatus.OK);
}

    //create hoc sinh
    @PostMapping("/create")
    public ResponseEntity<HocSinh> createhs(@Valid @RequestBody HocSinhDTO hocSinhDTO)
    {
        return new ResponseEntity<>(hocSinhService.create(hocSinhDTO),HttpStatus.OK);
    }
    //update hoc sinh
    @PutMapping("update")
    public ResponseEntity<HocSinh> updateStudent(@Valid @RequestBody HocSinhDTO hocSinhDTO){
        return new ResponseEntity<>(hocSinhService.update(hocSinhDTO),HttpStatus.OK);
    }
//detele hocSinh
@DeleteMapping("delete/{idNguoiDung}")
public ResponseEntity<Boolean> deleteStudent(@PathVariable("idNguoiDung") String id){
    return new ResponseEntity<>(hocSinhService.delete(id),HttpStatus.OK);
}

    //detail hoc sinh
    @GetMapping("/detail/{id}")
    public ResponseEntity<HocSinh> detail(@PathVariable("id") String id)
    {
        return new ResponseEntity<>(hocSinhService.detailById(id),HttpStatus.OK);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex) {
        Map<String, String> details = new HashMap<>();
        details.put("error", ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Record Not Found", false, details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}