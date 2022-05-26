package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.dto.request.GiaoVienDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GiaoVienService {

    List<GiaoVien> findAll();
    List<GiaoVien> searchHocSinhByTen(String ten);
    List<GiaoVien> searchHocSinhByBan(String ten);
    List<GiaoVien> searchHocSinhByTenBan(String ten,String ban);

    GiaoVien create(GiaoVienDTO giaoVienDTO);
    GiaoVien update(GiaoVienDTO giaoVienDTO);
    Boolean delete(String id);
    GiaoVien detail(String id);

}
