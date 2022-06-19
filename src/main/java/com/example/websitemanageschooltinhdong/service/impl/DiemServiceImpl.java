package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.DiemMonHoc;
import com.example.websitemanageschooltinhdong.domain.HocKiHocSinh;
import com.example.websitemanageschooltinhdong.domain.MonHoc;
import com.example.websitemanageschooltinhdong.dto.request.DiemHocSinhDTO;
import com.example.websitemanageschooltinhdong.repository.DiemRepository;
import com.example.websitemanageschooltinhdong.repository.HocKiHocSinhRepository;
import com.example.websitemanageschooltinhdong.repository.MonHocRepository;
import com.example.websitemanageschooltinhdong.service.DiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiemServiceImpl implements DiemService {
    @Autowired
    DiemRepository diemRepository;
    @Autowired
    MonHocRepository monHocRepository;
    @Autowired
    HocKiHocSinhRepository hocKiHocSinhRepository;

    @Override
    public List<DiemMonHoc> getDiemByHocSinh(String id) {
        return diemRepository.findAllByHocKiHocSinh_HocSinhId(id);
    }

    @Override
    public List<DiemMonHoc> getDiemByHocSinhAndNamHoc(String idhs, int idNamHoc) {
        return diemRepository.findAllByHocKiHocSinhandNamHoc_Id(idhs, idNamHoc);
    }

    @Override
    public DiemMonHoc updateDiemByHocSinh(DiemHocSinhDTO diemHocSinhDTO) {
        DiemMonHoc diemMonHoc = new DiemMonHoc();
        //id mon hoc
        MonHoc monHoc = monHocRepository.findById(diemHocSinhDTO.getIdMonHoc()).get();
        //id hoc ki
        HocKiHocSinh hocKiHocSinh = hocKiHocSinhRepository.findById(diemHocSinhDTO.getIdHocKiHocSinh()).get();
        diemMonHoc.setId(diemHocSinhDTO.getId());
        diemMonHoc.setDiemCuoiKy(diemHocSinhDTO.getDiemCuoiKy());
        diemMonHoc.setDiemGiuaKy(diemHocSinhDTO.getDiemGiuaKy());
        diemMonHoc.setHocKiHocSinh(hocKiHocSinh);
        diemMonHoc.setMonHoc(monHoc);
        return diemRepository.save(diemMonHoc);
    }
}
