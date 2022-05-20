package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.repository.HocSinhRespository;
import com.example.websitemanageschooltinhdong.service.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HocSinhServiceImpl implements HocSinhService {
    @Autowired
    HocSinhRespository hocSinhRespository;

    @Override
    public List<HocSinh> searchHocSinhByTen(String ten) {
        return hocSinhRespository.findAllByTenContaining(ten);
    }

    @Override
    public List<HocSinh> searchHocSinhByTenKhoi(String ten, int khoi) {
        return hocSinhRespository.findAllByTenContainingAndLop_Khoi_Id(ten,khoi);
    }

    @Override
    public List<HocSinh> searchHocSinhByTenKhoiLop(String ten, int lop, int khoi) {
        return hocSinhRespository.findAllByTenContainingAndLop_IdAndLop_Khoi_Id(ten, lop, khoi);
    }

    @Override
    public List<HocSinh> searchHocSinhAll() {
        return hocSinhRespository.findAll();
    }
}
