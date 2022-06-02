package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.CauHoi;
import com.example.websitemanageschooltinhdong.dto.request.CauHoiDTO;
import org.springframework.stereotype.Service;

@Service
public interface CauHoiService {
    CauHoi createCauHoi(CauHoiDTO cauHoi);

}
