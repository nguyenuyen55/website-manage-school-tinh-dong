package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.*;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhDTO;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhSearchDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.*;
import com.example.websitemanageschooltinhdong.service.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HocSinhServiceImpl implements HocSinhService {
    @Autowired
    DiemRepository diemRepository;
    @Autowired
    MonHocRepository monHocRepository;
    @Autowired
    HocSinhRespository hocSinhRespository;
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    HocSinhLopRepository hocSinhLopRepository;
    @Autowired
    LopRepository lopRepository;
    @Autowired
    HocKiRepository hocKiRepository;
    @Autowired
    HocKiHocSinhRepository hocKiHocSinhRepository;

    @Autowired
    NamHocRepository namHocRepository;
    private final PasswordEncoder passwordEncoder;

    public HocSinhServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }




    @Override
    public List<HocSinh> findALL() {
        List<HocSinh> findAll = hocSinhRespository.findAll();
        if (findAll.size() == 0) {
            throw new RecordNotFoundException("Không tìm thấy sinh viên nào");
        }
        return findAll;
    }

    @Override
    public List<HocSinhSearchDTO> findALLSearch(String name) {
        List<HocSinh> hocSinhList = hocSinhRespository.findAllByTenContaining(name);
        List<HocSinhSearchDTO> hocSinhSearchDTOS= new ArrayList<>();
      for(HocSinh hocSinh :hocSinhList){
          HocSinhLop  hocSinhLop = hocSinhLopRepository.findByHocSinh_IdAndActiveTrue(hocSinh.getId());

          HocSinhSearchDTO hocSinhSearchDTO = HocSinhSearchDTO.builder()
                  .ten(hocSinh.getTen())
                  .diaChi(hocSinh.getDiaChi())
                  .ngaysinh(hocSinh.getNgaySinh())
                  .khoa(hocSinhLop.getLop().getNamHoc().getYear())
                  .tenlop(hocSinhLop.getLop().getTen())
                  .build();
          hocSinhSearchDTOS.add(hocSinhSearchDTO);
      }

        return hocSinhSearchDTOS;
    }


    @Override
    public List<HocSinh> findAllIdLop(int id) {
        return hocSinhRespository.findAllByHocSinhLops(id);
    }

    @Override
    public HocSinh create(HocSinhDTO hocSinhDTO) {


        Optional<Lop> lop = lopRepository.findById(hocSinhDTO.getIdLop());
        if (!lop.isPresent()) {
            throw new RecordNotFoundException("Lớp này không tồn tại");
        }

//set hoc sinh
        HocSinh hocSinh = HocSinh.builder()
                .ten(hocSinhDTO.getTen())
                .diaChi(hocSinhDTO.getDiaChi())
                .gioiTinh(hocSinhDTO.getGioiTinh())
                .trangThai("Đang học")
                .danToc(hocSinhDTO.getDanToc())
                .tenBo(hocSinhDTO.getTenBo())
                .tenMe(hocSinhDTO.getTenMe())
                .tonGiao(hocSinhDTO.getTonGiao())
                .sdtBoMe(hocSinhDTO.getSdtBoMe())
                .hinhAnh(hocSinhDTO.getHinhAnh())
                .ngaySinh(hocSinhDTO.getNgaySinh())
                .build();

        HocSinh hocsinhreal = hocSinhRespository.save(hocSinh);

        //set nguoi nguoi cũng nhu tao nguoi dùng tendn, mk ,quyen,
        NguoiDung nguoiDung = NguoiDung.builder()
                .tenDangNhap(hocsinhreal.getId().toLowerCase())
                .matKhau(passwordEncoder.encode("hs123"))
                .quyen("ROLE_STUDENT")
                .build();
        hocsinhreal.setNguoiDung(nguoiDung);
        //save một đối tượng chứa id lop và id hoc sinh
//        tạo mới một đối tượng giáo viên lóp mới
        HocSinhLop hocSinhLop = new HocSinhLop();
        hocSinhLop.setHocSinh(hocSinhRespository.save(hocsinhreal));
        hocSinhLop.setLop(lop.get());
        hocSinhLop.setActive(true);
        hocSinhLopRepository.save(hocSinhLop);
////check nam hoc va lay doi tuong nam hoc
////tạo list học kì đã có sẵn
        int yearCurrent = new Date().getYear() + 1900;
        List<HocKi> hocKis = hocKiRepository.findAllByNamHocYear(yearCurrent);
        NamHoc namhocReal = null;
        HocKi hockiNew = null;
        HocKi hockiNew1 = null;
        if (hocKis.size() != 0) {
            hockiNew = hocKis.get(0);
            hockiNew1 = hocKis.get(1);
        } else {
            NamHoc namHocNew = new NamHoc();
            namHocNew.setYear(new Date().getYear());
            namhocReal = namHocRepository.save(namHocNew);
            //Tạo học kìs
            HocKi hocKi = new HocKi();
            hocKi.setName("học kì 1");
            hocKi.setNamHoc(namhocReal);
            HocKi hocKi1 = new HocKi();
            hocKi1.setName("học kì 2");
            hocKi1.setNamHoc(namhocReal);
            hockiNew = hocKiRepository.save(hocKi);
            hockiNew1 = hocKiRepository.save(hocKi1);
        }
//        //nếu có thì lấy học kì của năm học đó không có thì tạo mới
        HocKiHocSinh hocKiHocSinh = new HocKiHocSinh();
        hocKiHocSinh.setHocSinh(hocsinhreal);
        hocKiHocSinh.setHocKi(hockiNew);
        //set tung mon cua khoi dó cho diem mon hoc của 2 kì
        HocKiHocSinh hocKiHocSinhReal = hocKiHocSinhRepository.save(hocKiHocSinh);
        setdiem(hocSinhLop.getLop(), hocKiHocSinhReal);
//        System.out.println(hocKiHocSinh);
        HocKiHocSinh hocKiHocSinh2 = new HocKiHocSinh();
        hocKiHocSinh2.setHocSinh(hocsinhreal);
        hocKiHocSinh2.setHocKi(hockiNew1);
//        System.out.println(hocKiHocSinh2);
        HocKiHocSinh hocKiHocSinhReal1 = hocKiHocSinhRepository.save(hocKiHocSinh2);
        setdiem(hocSinhLop.getLop(), hocKiHocSinhReal1);
        return hocSinhRespository.save(hocsinhreal);
    }

    @Override
    public HocSinh update(HocSinhDTO hocSinhDTO) {
        //check id hoc sinh
        Optional<HocSinh> hocSinhCheck = hocSinhRespository.findById(hocSinhDTO.getId());
        if (!hocSinhCheck.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy học sinh này");
        }
        //set lop
//        Optional<Lop> lop = lopRepository.findById(hocSinhDTO.getIdLop());
//        if (!lop.isPresent()) {
//            throw new RecordNotFoundException("Not found lớp");
//        }
        Optional<NguoiDung> nguoiDung=null;
if(hocSinhCheck.get().getNguoiDung()!=null) {
    nguoiDung = nguoiDungRepository.findById(hocSinhCheck.get().getNguoiDung().getId());
    if (!nguoiDung.isPresent()) {
        throw new RecordNotFoundException("Not found người dùng");
    }
}
        //set hoc sinh
        HocSinh hocSinh = HocSinh.builder()
                .id(hocSinhDTO.getId())
                .ten(hocSinhDTO.getTen())
                .diaChi(hocSinhDTO.getDiaChi())
                .gioiTinh(hocSinhDTO.getGioiTinh())
                .trangThai(hocSinhDTO.getTrangThai())
                .danToc(hocSinhDTO.getDanToc())
                .tenBo(hocSinhDTO.getTenBo())
                .tenMe(hocSinhDTO.getTenMe())
                .tonGiao(hocSinhDTO.getTonGiao())
                .sdtBoMe(hocSinhDTO.getSdtBoMe())
                .hinhAnh(hocSinhDTO.getHinhAnh())
                .ngaySinh(hocSinhDTO.getNgaySinh())
                .build();

        return hocSinhRespository.save(hocSinh);
    }

    @Override
    public HocSinh detailById(String id) {
        Optional<HocSinh> hocSinh = hocSinhRespository.findById(id);
        if (!hocSinh.isPresent()) {
            throw new RecordNotFoundException("Không tìm thấy học sinh này");
        }
        return hocSinh.get();
    }

    @Override
    public Boolean delete(String id) {
        //check id hoc sinh
        Optional<HocSinh> hocSinhCheck = hocSinhRespository.findById(id);
        if (!hocSinhCheck.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy học sinh này");
        }
        if (hocSinhCheck.get().getNguoiDung() != null) {
            Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(hocSinhCheck.get().getNguoiDung().getId());
            if (nguoiDung.isPresent()) {
                hocSinhCheck.get().setNguoiDung(null);
                hocSinhRespository.save(hocSinhCheck.get());
            }
            hocSinhRespository.save(hocSinhCheck.get());
            nguoiDung.get().setTenDangNhap("");
            nguoiDungRepository.save(nguoiDung.get());
//            nguoiDungRepository.delete(nguoiDung.get());
        }
        List<HocSinhLop> hocSinhLops = hocSinhLopRepository.findAllByHocSinh_Id(id);

        for (HocSinhLop hocSinhLop : hocSinhLops) {
            hocSinhLopRepository.delete(hocSinhLop);
        }


        //xoá điểm môn học
        List<DiemMonHoc> diemMonHocs = diemRepository.findAllByHocKiHocSinh_HocSinhId(hocSinhCheck.get().getId());
        if (diemMonHocs.size() != 0) {
            for (DiemMonHoc diemMonHoc : diemMonHocs) {
                diemRepository.delete(diemMonHoc);
            }
        }

        //xoá học kì học sinh
        List<HocKiHocSinh> hocKiHocSinhs = hocKiHocSinhRepository.findAllByHocSinh_Id(hocSinhCheck.get().getId());
        if (hocKiHocSinhs.size() != 0) {
            for (HocKiHocSinh hocKiHocSinh : hocKiHocSinhs) {
                hocKiHocSinh.setHocSinh(null);
                hocKiHocSinhRepository.save(hocKiHocSinh);
                hocKiHocSinhRepository.delete(hocKiHocSinh);
            }
        }
        hocSinhRespository.deleteById(id);
        return true;
    }


    public void setdiem(Lop lop, HocKiHocSinh hocKiHocSinh) {
        switch (lop.getKhoi().getTen()) {
            case "Khối 1":
                //list ra môn học của khối đó
                List<MonHoc> monHocs = monHocRepository.findAllByKhoiId(lop.getKhoi().getId());
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
                List<MonHoc> monHocs3 = monHocRepository.findAllByKhoiId(lop.getKhoi().getId());
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
                List<MonHoc> monHocs4 = monHocRepository.findAllByKhoiId(lop.getKhoi().getId());
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
                List<MonHoc> monHocs5 = monHocRepository.findAllByKhoiId(lop.getKhoi().getId());
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
