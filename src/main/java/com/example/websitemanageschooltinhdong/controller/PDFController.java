package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import com.example.websitemanageschooltinhdong.domain.GiaoVienLop;
import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.repository.GiaoVienLopRepository;
import com.example.websitemanageschooltinhdong.repository.GiaoVienRepository;
import com.example.websitemanageschooltinhdong.repository.HocSinhRespository;
import com.example.websitemanageschooltinhdong.service.impl.FilesExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exportPdf")
public class PDFController {
    @Autowired
    GiaoVienRepository giaoVienRepository;
    @Autowired
    GiaoVienLopRepository giaoVienLopRepository;
    @Autowired
    FilesExporter filesExporter;

    @Autowired
    HocSinhRespository hocSinhRespository;

    @GetMapping("/{idTeacher}")
    public void exportToPDF(@PathVariable("idTeacher") String idTeacher, HttpServletResponse response) throws IOException {
//        List<HocSinh> listhocsinh =
        int idLop = 0;
        Optional<GiaoVien> giaoVien = giaoVienRepository.findById(idTeacher);

        GiaoVienLop giaoVienLop = giaoVienLopRepository.findByGiaoVien_IdAndActiveTrue(giaoVien.get().getId());
        idLop = giaoVienLop.getLop().getId();
        List<HocSinh> hocSinhList = hocSinhRespository.findAllByHocSinhLopsAdmin(idLop);
        filesExporter.exportToPDF(hocSinhList, response);
    }
}
