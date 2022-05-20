package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.service.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class HocSinhController {
    @Autowired
    HocSinhService hocSinhService;

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
}
