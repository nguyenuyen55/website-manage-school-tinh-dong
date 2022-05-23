package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.ChiTietThoiKhoaBieu;
import com.example.websitemanageschooltinhdong.repository.ChiTietBieuRespository;
import com.example.websitemanageschooltinhdong.service.ChiTietBieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThoiKhoaBieuServiceImpl implements ChiTietBieuService {
    @Autowired
    ChiTietBieuRespository chiTietBieuRespository;

    @Override
    public List<ChiTietThoiKhoaBieu> findAllByIdLop(int idlop) {
        System.out.println(chiTietBieuRespository.findAllByIdLop(idlop));
        return chiTietBieuRespository.findAllByIdLop(idlop);
    }
}
