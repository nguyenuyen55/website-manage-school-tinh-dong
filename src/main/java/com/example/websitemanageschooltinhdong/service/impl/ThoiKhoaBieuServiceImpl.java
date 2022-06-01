package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.ChiTietThoiKhoaBieu;
import com.example.websitemanageschooltinhdong.domain.Lop;
import com.example.websitemanageschooltinhdong.domain.MonHoc;
import com.example.websitemanageschooltinhdong.domain.ThoiKhoaBieu;
import com.example.websitemanageschooltinhdong.dto.request.TimeTableDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.ChiTibetBleuRepository;
import com.example.websitemanageschooltinhdong.repository.LopRepository;
import com.example.websitemanageschooltinhdong.repository.MonHocRepository;
import com.example.websitemanageschooltinhdong.repository.ThoiKhoaBieuRepository;
import com.example.websitemanageschooltinhdong.service.ChiTietBieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThoiKhoaBieuServiceImpl implements ChiTietBieuService {
    @Autowired
    ChiTibetBleuRepository chiTietBieuRespository;
    @Autowired
    MonHocRepository monHocRepository;
    @Autowired
    LopRepository lopRepository;
    @Autowired
    ThoiKhoaBieuRepository thoiKhoaBieuRepository;

    @Override
    public List<ChiTietThoiKhoaBieu> findAllByIdLop(int idlop) {
        return chiTietBieuRespository.findAllByIdLop(idlop);
    }

    @Override
    public List<ChiTietThoiKhoaBieu> updateTimeTable(List<TimeTableDTO> timeTableDTOS) {
        List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieus = new ArrayList<>();
        for (TimeTableDTO timeTableDTO : timeTableDTOS) {
            //check thoi khoa biêu
            Optional<ThoiKhoaBieu> thoiKhoaBieu = thoiKhoaBieuRepository.findById(timeTableDTO.getIdThoiKhoaBieu());
            if (!thoiKhoaBieu.isPresent()) {
                throw new RecordNotFoundException("Không tìm thấy thời khoá biểu này");
            }
//            // check mon học và lay dc môn học đó
            Optional<MonHoc> monHoc = monHocRepository.findById(timeTableDTO.getIdMonHoc());
            if (!monHoc.isPresent()) {
                throw new RecordNotFoundException("Không tìm thấy môn học này");
            }
            // set các thuộc tính vào chi tiết thời khoá biểu xong lưu nó lại
            ChiTietThoiKhoaBieu chiTietThoiKhoaBieu = ChiTietThoiKhoaBieu.builder()
                    .thoiKhoaBieu(thoiKhoaBieu.get())
                    .monHoc(monHoc.get())
                    .thu(timeTableDTO.getThu())
                    .thuTu(timeTableDTO.getThuTu())
                    .id(timeTableDTO.getId())
                    .build();
            chiTietThoiKhoaBieus.add(chiTietBieuRespository.save(chiTietThoiKhoaBieu));
        }
        return chiTietThoiKhoaBieus;
    }

    @Override
    public List<ChiTietThoiKhoaBieu> createTimeTable(List<TimeTableDTO> timeTableDTOS, int idlop) {
        //check lop
        Optional<Lop> lop = lopRepository.findById(idlop);
        if (!lop.isPresent()) {
            throw new RecordNotFoundException("lớp này không tồn tại");
        }
        //tạo thơi khoa bieu cho lop dó
        ThoiKhoaBieu thoiKhoaBieu = new ThoiKhoaBieu();
        thoiKhoaBieu.setLop(lop.get());
        ThoiKhoaBieu thoiKhoaBieureal = thoiKhoaBieuRepository.save(thoiKhoaBieu);
        //lấy đối tượng thời khoá biểu set vô thời khoá biểu chi tiết
        List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieus = new ArrayList<>();
        for (TimeTableDTO timeTableDTO : timeTableDTOS) {
//            // check mon học và lay dc môn học đó
            Optional<MonHoc> monHoc = monHocRepository.findById(timeTableDTO.getIdMonHoc());
            if (!monHoc.isPresent()) {
                throw new RecordNotFoundException("Không tìm thấy môn học này");
            }
            // set các thuộc tính vào chi tiết thời khoá biểu xong lưu nó lại
            ChiTietThoiKhoaBieu chiTietThoiKhoaBieu = ChiTietThoiKhoaBieu.builder()
                    .thoiKhoaBieu(thoiKhoaBieureal)
                    .monHoc(monHoc.get())
                    .thu(timeTableDTO.getThu())
                    .thuTu(timeTableDTO.getThuTu())
                    .build();
            chiTietThoiKhoaBieus.add(chiTietBieuRespository.save(chiTietThoiKhoaBieu));
        }
        return chiTietThoiKhoaBieus;
    }
}
