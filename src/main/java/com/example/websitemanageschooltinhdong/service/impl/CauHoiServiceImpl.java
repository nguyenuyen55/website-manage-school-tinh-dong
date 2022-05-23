package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.CauHoi;
import com.example.websitemanageschooltinhdong.repository.CauHoiRespository;
import com.example.websitemanageschooltinhdong.service.CauHoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CauHoiServiceImpl implements CauHoiService {
    @Autowired
    CauHoiRespository cauHoiRespository;
    @Override
    public CauHoi createCauHoi(CauHoi cauHoi) {
        return cauHoiRespository.save(cauHoi);
    }
}
