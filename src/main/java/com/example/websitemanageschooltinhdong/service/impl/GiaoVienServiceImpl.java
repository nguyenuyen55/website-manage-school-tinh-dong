package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import com.example.websitemanageschooltinhdong.repository.GiaoVienRepository;
import com.example.websitemanageschooltinhdong.service.GiaoVienService;
import com.example.websitemanageschooltinhdong.service.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GiaoVienServiceImpl implements GiaoVienService {
    @Autowired
    GiaoVienRepository giaoVienRepository;

    @Override
    public List<GiaoVien> findAll() {
        return giaoVienRepository.findAll();
    }

    @Override
    public List<GiaoVien> searchHocSinhByTen(String ten) {
        return giaoVienRepository.findAllByTenContaining(ten);
    }

    @Override
    public List<GiaoVien> searchHocSinhByBan(String ban) {
        return giaoVienRepository.findAllByPhongBan_Id(ban);
    }

    @Override
    public List<GiaoVien> searchHocSinhByTenBan(String ten, String ban) {
        return giaoVienRepository.findAllByTenContainingAndPhongBan_Id(ten,ban);
    }
}
