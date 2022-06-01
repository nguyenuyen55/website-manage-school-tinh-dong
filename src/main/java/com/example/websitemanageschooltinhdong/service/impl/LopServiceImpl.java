package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.*;
import com.example.websitemanageschooltinhdong.dto.request.GiaoVienLopDTO;
import com.example.websitemanageschooltinhdong.dto.request.LopDTO;
import com.example.websitemanageschooltinhdong.dto.response.LopGiaoVienResponse;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.*;
import com.example.websitemanageschooltinhdong.service.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LopServiceImpl implements LopService {
    @Autowired
    DiemRepository diemRepository;
    @Autowired
    MonHocRepository monHocRepository;
    @Autowired
    HocKiHocSinhRepository hocKiHocSinhRepository;
    @Autowired
    HocKiRepository hocKiRepository;
    @Autowired
    LopRepository lopRepository;
    @Autowired
    NamHocRepository namHocRepository;
    @Autowired
    HocSinhRespository hocSinhRespository;
    @Autowired
    KhoiRepository khoiRepository;
    @Autowired
    GiaoVienRepository giaoVienRepository;

    @Override
    public Lop create(LopDTO lopDTO) {
        Optional<Khoi> khoiOptional = khoiRepository.findById(lopDTO.getIdKhoi());
        if (!khoiOptional.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy khối này");
        }
        NamHoc namHocOptional = namHocRepository.findByYear(lopDTO.getYearCurrent());
        if (namHocOptional == null) {
            throw new RecordNotFoundException("không tìm thấy năm này");
        }
        Lop lop = Lop.builder()
                .ten(lopDTO.getName())
                .khoi(khoiOptional.get())
                .namHoc(namHocOptional)
                .build();
        return lopRepository.save(lop);
    }

    @Override
    public Lop update(LopDTO lopDTO) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public Lop detail(int id) {
        return lopRepository.findById(id).get();
    }

    @Override
    public List<Lop> findAll() {
        return lopRepository.findAll();
    }

    @Override
    public List<LopGiaoVienResponse> findAllLop() {
        List<LopGiaoVienResponse> responseList = new ArrayList<>();
        List<Lop> lopList = lopRepository.findAll();

        for (Lop lop : lopList) {
            LopGiaoVienResponse lopGiaoVienResponse = new LopGiaoVienResponse();
            lopGiaoVienResponse.setTenLop(lop.getTen());
            if (lop.getGiaoVien() != null) {
                GiaoVien giaoVien = giaoVienRepository.findByLopgvId(lop.getId());
                lopGiaoVienResponse.setCoChuNhiem(giaoVien.getTen());
                lopGiaoVienResponse.setIdgv(giaoVien.getId());
            }
            lopGiaoVienResponse.setIdLop(lop.getId());
            lopGiaoVienResponse.setNiemKhoa(lop.getNamHoc().getYear());
            responseList.add(lopGiaoVienResponse);
        }
        return responseList;
    }

    @Override
    public Boolean updadegvlop(GiaoVienLopDTO giaoVienLopDTO) {
        Optional<Lop> lopOptional = lopRepository.findById(giaoVienLopDTO.getIdLop());
        if (!lopOptional.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy lớp này");
        }
        Optional<GiaoVien> giaoVienOptional = giaoVienRepository.findById(giaoVienLopDTO.getIdGv());
        if (!giaoVienOptional.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy giáo viên này");
        }
        giaoVienOptional.get().setLopgv(lopOptional.get());
        giaoVienRepository.save(giaoVienOptional.get());
        return true;
    }

    @Override
    public Boolean updateLenLop(String ten, int year, String idgv, int idlop) {

        //tạo 1 lớp lớn hơn với 1 năm lớp hơn
//        String tenCut = String.valueOf(ten.charAt(0));
        String name = String.valueOf(ten.charAt(1));
        String tenCut = String.valueOf(ten.charAt(0));
        Khoi khoi = new Khoi();

        switch (String.valueOf(ten.charAt(0))) {
            case "1":
                name = "2" + name;
                khoi = khoiRepository.findById(2).get();
                break;
            case "2":
                name = "3" + name;
                khoi = khoiRepository.findById(3).get();
                break;
            case "3":
                name = "4" + name;
                khoi = khoiRepository.findById(4).get();
                break;
            case "4":
                name = "5" + name;
                khoi = khoiRepository.findById(5).get();
                break;
        }
        //tạo list học kì đã có sẵn
        List<HocKi> hocKis = hocKiRepository.findAllByNamHocYear(year + 1);
        NamHoc namhocReal = namHocRepository.findByYear(year + 1);
        HocKi hockiNew = null;
        HocKi hockiNew1 = null;
        if (hocKis.size() != 0) {
            hockiNew = hocKis.get(0);
            hockiNew1 = hocKis.get(1);
        }
        if (namhocReal == null) {
            NamHoc namHocNew = new NamHoc();
            namHocNew.setYear(year + 1);
            namhocReal = namHocRepository.save(namHocNew);
            //Tạo học kì
            HocKi hocKi = new HocKi();
            hocKi.setName("học kì 1");
            hocKi.setNamHoc(namhocReal);
            HocKi hocKi1 = new HocKi();
            hocKi1.setName("học kì 2");
            hocKi1.setNamHoc(namhocReal);
            hockiNew = hocKiRepository.save(hocKi);
            hockiNew1 = hocKiRepository.save(hocKi1);
        }
        //tạo 1 lớp mới
        Lop lop = Lop.builder()
                .ten(name)
                .khoi(khoi)
                .namHoc(namhocReal)
                .build();

        Lop lopNew = lopRepository.save(lop);
//set lai lop cho giao vien
        GiaoVien giaoViennew = giaoVienRepository.findById(idgv).get();
        giaoViennew.setLopgv(lopNew);
        giaoVienRepository.save(giaoViennew);

//set list học sinh lại
        //tạo 2 kì tiếp theo cho học sinh
        List<HocSinh> hocSinhs = hocSinhRespository.findAllByLopId(idlop);

        for (HocSinh hocSinh : hocSinhs) {
            hocSinh.setLop(lopNew);
            hocSinhRespository.save(hocSinh);
            HocKiHocSinh hocKiHocSinh = new HocKiHocSinh();
            hocKiHocSinh.setHocSinh(hocSinh);
            hocKiHocSinh.setHocKi(hockiNew);
            //set tung mon cua khoi dó cho diem mon hoc của 2 kì
            HocKiHocSinh hocKiHocSinhReal = hocKiHocSinhRepository.save(hocKiHocSinh);
            setdiem(hocSinh, hocKiHocSinhReal);
            HocKiHocSinh hocKiHocSinh2 = new HocKiHocSinh();
            hocKiHocSinh2.setHocSinh(hocSinh);
            hocKiHocSinh2.setHocKi(hockiNew1);
            HocKiHocSinh hocKiHocSinhReal1 = hocKiHocSinhRepository.save(hocKiHocSinh2);
            setdiem(hocSinh, hocKiHocSinhReal1);
        }
        return true;
    }

    public void setdiem(HocSinh hocSinh, HocKiHocSinh hocKiHocSinh) {
        switch (hocSinh.getLop().getKhoi().getTen()) {
            case "Khối 1":
                //list ra môn học của khối đó
                List<MonHoc> monHocs = monHocRepository.findAllByKhoiId(1);
                for (MonHoc monHoc : monHocs) {
                    if (monHoc.getTen().equals("Toán") || monHoc.getTen().equals("Tiếng việt") || monHoc.getTen().equals("Tiếng anh")) {
                        DiemMonHoc diemMonHoc = new DiemMonHoc();
                        diemMonHoc.setMonHoc(monHoc);
                        diemMonHoc.setHocKiHocSinh(hocKiHocSinh);
                        diemRepository.save(diemMonHoc);
                    }
                }
                break;
            case "Khối 2":
                List<MonHoc> monHocs2 = monHocRepository.findAllByKhoiId(2);
                for (MonHoc monHoc : monHocs2) {
                    if (monHoc.getTen().equals("Toán") || monHoc.getTen().equals("Tiếng việt") || monHoc.getTen().equals("Tiếng anh")) {
                        DiemMonHoc diemMonHoc = new DiemMonHoc();
                        diemMonHoc.setMonHoc(monHoc);
                        diemMonHoc.setHocKiHocSinh(hocKiHocSinh);
                        diemRepository.save(diemMonHoc);
                    }
                }
                break;
            case "Khối 3":
                List<MonHoc> monHocs3 = monHocRepository.findAllByKhoiId(3);
                for (MonHoc monHoc : monHocs3) {
                    if (monHoc.getTen().equals("Toán") || monHoc.getTen().equals("Tiếng việt") || monHoc.getTen().equals("Tiếng anh")) {
                        DiemMonHoc diemMonHoc = new DiemMonHoc();
                        diemMonHoc.setMonHoc(monHoc);
                        diemMonHoc.setHocKiHocSinh(hocKiHocSinh);
                        diemRepository.save(diemMonHoc);
                    }
                }
                break;
            case "Khối 4":
                List<MonHoc> monHocs4 = monHocRepository.findAllByKhoiId(4);
                for (MonHoc monHoc : monHocs4) {
                    if (monHoc.getTen().equals("Toán") || monHoc.getTen().equals("Tiếng việt") || monHoc.getTen().equals("Tiếng anh") || monHoc.getTen().equals("Lịch sử và địa lí")) {
                        DiemMonHoc diemMonHoc = new DiemMonHoc();
                        diemMonHoc.setMonHoc(monHoc);
                        diemMonHoc.setHocKiHocSinh(hocKiHocSinh);
                        diemRepository.save(diemMonHoc);
                    }
                }
                break;
            case "Khối 5":
                List<MonHoc> monHocs5 = monHocRepository.findAllByKhoiId(5);
                for (MonHoc monHoc : monHocs5) {
                    if (monHoc.getTen().equals("Toán") || monHoc.getTen().equals("Tiếng việt") || monHoc.getTen().equals("Tiếng anh") || monHoc.getTen().equals("Lịch sử và địa lí")) {
                        DiemMonHoc diemMonHoc = new DiemMonHoc();
                        diemMonHoc.setMonHoc(monHoc);
                        diemMonHoc.setHocKiHocSinh(hocKiHocSinh);
                        diemRepository.save(diemMonHoc);
                    }
                }
                break;
        }
    }
}