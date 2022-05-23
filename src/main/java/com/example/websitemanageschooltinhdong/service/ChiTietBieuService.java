package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.ChiTietThoiKhoaBieu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChiTietBieuService {

    List<ChiTietThoiKhoaBieu> findAllByIdLop(int idlop);

}
