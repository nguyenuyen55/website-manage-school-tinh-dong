package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HocSinhService {
    List<HocSinh> searchHocSinhByTen(String ten);
    List<HocSinh> searchHocSinhByTenKhoi(String ten,int khoi);
    List<HocSinh> searchHocSinhByTenKhoiLop(String ten,int lop,int khoi);
    List<HocSinh> searchHocSinhAll();

}
