package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.DiemMonHoc;
import com.example.websitemanageschooltinhdong.dto.request.DiemHocSinhDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiemService {
List<DiemMonHoc> getDiemByHocSinh(String id);
List<DiemMonHoc> getDiemByHocSinhAndNamHoc(String idhs, int idNamHoc);
DiemMonHoc updateDiemByHocSinh(DiemHocSinhDTO diemHocSinhDTO);
}
