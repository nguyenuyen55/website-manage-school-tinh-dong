package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.dto.request.BaiGiangDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaiGiangService {
    //find by id
    BaiGiang findById(int id);
    BaiGiang createBaiGiang(BaiGiangDTO baiGiangDTO);
    BaiGiang updateBaiGiang(BaiGiangDTO baiGiangDTO);
    Boolean deleteBaiGiang(int id);
    List<BaiGiang> getall();
    List<BaiGiang> getAllByMonHocAndTenChuongHoc(Integer idchuong,String idmon,String name);
}
