package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HocSinhService {
    List<HocSinh> searchHocSinhByTen(String ten);

    List<HocSinh> searchHocSinhByTenKhoi(String ten, int khoi);

    List<HocSinh> searchHocSinhByTenKhoiLop(String ten, int lop, int khoi);

    List<HocSinh> searchHocSinhAll();

    List<HocSinh> findALL();

    //find all hocSinh
    List<HocSinh> findAllIdLop(int id);

    HocSinh create(HocSinhDTO hocSinhDTO);

    HocSinh update(HocSinhDTO hocSinhDTO);

    HocSinh detailById(String id);

    Boolean delete(String id);
}
