package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.Lop;
import com.example.websitemanageschooltinhdong.dto.request.GiaoVienLopDTO;
import com.example.websitemanageschooltinhdong.dto.request.LopDTO;
import com.example.websitemanageschooltinhdong.dto.response.LopGiaoVienResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LopService {

    Lop create(LopDTO lopDTO);
    Lop update(LopDTO lopDTO);
    Boolean delete(int id);
    Lop detail(int id);
    List<Lop> findAll();
    List<Lop> findAllByBlock(int id);
    List<LopGiaoVienResponse> findAllLop();
    List<LopGiaoVienResponse> findAllLopByKhoi(int idkhoi,int year);
    Boolean updadegvlop(GiaoVienLopDTO giaoVienLopDTO);
    Boolean updateLenLop(String ten , int year,String idgv,int idLop);
}
