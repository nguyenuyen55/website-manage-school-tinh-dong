package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.CauHoi;
import com.example.websitemanageschooltinhdong.domain.ChuDe;
import com.example.websitemanageschooltinhdong.domain.Khoi;
import com.example.websitemanageschooltinhdong.dto.request.CauHoiDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.CauHoiRespository;
import com.example.websitemanageschooltinhdong.repository.ChuDeRepository;
import com.example.websitemanageschooltinhdong.service.CauHoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CauHoiServiceImpl implements CauHoiService {
    @Autowired
    CauHoiRespository cauHoiRespository;
    @Autowired
    ChuDeRepository chuDeRepository;
    @Override
    public CauHoi createCauHoi(CauHoiDTO cauHoi) {
        Optional<ChuDe> chuDeOptional = chuDeRepository.findById(cauHoi.getIdChuDe());
        if (!chuDeOptional.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy chu de này");
        }
        CauHoi cauHoiReal = CauHoi.builder()
                .chuDe(chuDeOptional.get())
                .email(cauHoi.getEmail())
                .hoTen(cauHoi.getHoTen())
                .noiDung(cauHoi.getNoiDung())
                .phone(cauHoi.getPhone())
                .tieuDe(cauHoi.getTieuDe())
                .build();
        return cauHoiRespository.save(cauHoiReal);
    }
}
