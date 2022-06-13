package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.ChiTietThoiKhoaBieu;
import com.example.websitemanageschooltinhdong.dto.request.TimeTableDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChiTietBieuService {

    List<ChiTietThoiKhoaBieu> findAllByIdLop(int idlop);
    List<ChiTietThoiKhoaBieu> updateTimeTable(List<TimeTableDTO> chiTietThoiKhoaBieus);
    boolean createTimeTable(List<TimeTableDTO> chiTietThoiKhoaBieus,int idlop);

}
