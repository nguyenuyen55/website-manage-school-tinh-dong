package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.dto.request.BaiGiangDTO;
import org.springframework.stereotype.Service;

@Service
public interface BaiGiangService {
    //find by id
    BaiGiang findById(int id);
    BaiGiang createBaiGiang(BaiGiangDTO baiGiangDTO);
}
