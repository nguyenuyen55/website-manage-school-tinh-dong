package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.*;
import com.example.websitemanageschooltinhdong.dto.request.GiaoVienDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.*;
import com.example.websitemanageschooltinhdong.service.GiaoVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GiaoVienServiceImpl implements GiaoVienService {
    @Autowired
    GiaoVienRepository giaoVienRepository;
    @Autowired
    GiaoVienLopRepository giaoVienLopRepository;
    @Autowired
    LopRepository lopRepository;
    @Autowired
    BangCapRepository bangCapRepository;
    @Autowired
    PhongBanRepository phongBanRepository;
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<GiaoVien> findAll() {
        return giaoVienRepository.findAll();
    }

    @Override
    public List<GiaoVien> searchHocSinhByTen(String ten) {
        return giaoVienRepository.findAllByTenContaining(ten);
    }

    @Override
    public List<GiaoVien> searchHocSinhByBan(String ban) {
        return giaoVienRepository.findAllByPhongBan_Id(ban);
    }

    @Override
    public List<GiaoVien> searchHocSinhByTenBan(String ten, String ban) {
        return giaoVienRepository.findAllByTenContainingAndPhongBan_Id(ten, ban);
    }

    @Override
    public GiaoVien create(GiaoVienDTO giaoVienDTO) {
        // lop , set phong ban, bang , create nguoidung
//
//        //set lop
//        Optional<Lop> lop = lopRepository.findById(giaoVienDTO.getIdLop());
//        if (!lop.isPresent()) {
//            throw new RecordNotFoundException("không tìm thấy lớp");
//        }

//bang cap
        Optional<BangCap> bangCap = bangCapRepository.findById(giaoVienDTO.getIdBangCap());
        if (!bangCap.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy bẳng cấp");
        }
        //phong ban
        Optional<PhongBan> phongBan = phongBanRepository.findById(giaoVienDTO.getIdBan());
        if (!phongBan.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy phòng ban");
        }
        //create GiaoVien

        GiaoVien giaoVien = GiaoVien.builder()
                .bangCap(bangCap.get())
                .diaChi(giaoVienDTO.getDiaChi())
                .email(giaoVienDTO.getEmail())
                .gioiTinh(giaoVienDTO.getGioiTinh())
                .hinhAnh(giaoVienDTO.getHinhAnh())
                .ngaySinh(giaoVienDTO.getNgaySinh())
                .phongBan(phongBan.get())
                .ten(giaoVienDTO.getTen())
                .soDienThoai(giaoVienDTO.getSoDienThoai())
                .tenDaiHoc(giaoVienDTO.getTenDaiHoc())
                .build();
        GiaoVien giaovienReal = giaoVienRepository.save(giaoVien);
        //set nguoi nguoi cũng nhu tao nguoi dùng tendn, mk ,quyen,
        NguoiDung nguoiDung = NguoiDung.builder()
                .tenDangNhap(giaovienReal.getId().toLowerCase())
                .matKhau(passwordEncoder.encode("gv123"))
                .quyen("ROLE_TEACHER")
                .build();
        giaovienReal.setNguoiDung(nguoiDung);

        return giaoVienRepository.save(giaovienReal);
    }

    @Override
    public GiaoVien update(GiaoVienDTO giaoVienDTO) {
//check id giao vien
        Optional<GiaoVien> giaoVienOptional = giaoVienRepository.findById(giaoVienDTO.getId());
        if (!giaoVienOptional.isPresent()) {
            throw new RecordNotFoundException("giáo viên này không tồn tại");
        }

//
//        //set lop
//        Optional<Lop> lop = lopRepository.findById(giaoVienDTO.getIdLop());
//        if (!lop.isPresent()) {
//            throw new RecordNotFoundException("không tìm thấy lớp");
//        }

//bang cap
        Optional<BangCap> bangCap = bangCapRepository.findById(giaoVienDTO.getIdBangCap());
        if (!bangCap.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy bẳng cấp");
        }
        //phong ban
        Optional<PhongBan> phongBan = phongBanRepository.findById(giaoVienDTO.getIdBan());
        if (!phongBan.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy phòng ban");
        }
        //nguoi dung
        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(giaoVienDTO.getIdnguoiDung());
        if (!nguoiDung.isPresent()) {
            throw new RecordNotFoundException("không tìm thấyngười dùng này");
        }
        GiaoVien giaoVien = GiaoVien.builder()
                .id(giaoVienDTO.getId())
                .bangCap(bangCap.get())
                .diaChi(giaoVienDTO.getDiaChi())
                .email(giaoVienDTO.getEmail())
                .gioiTinh(giaoVienDTO.getGioiTinh())
                .hinhAnh(giaoVienDTO.getHinhAnh())
                .ngaySinh(giaoVienDTO.getNgaySinh())
                .phongBan(phongBan.get())
                .ten(giaoVienDTO.getTen())
                .soDienThoai(giaoVienDTO.getSoDienThoai())
                .tenDaiHoc(giaoVienDTO.getTenDaiHoc())
                .nguoiDung(nguoiDung.get())
                .build();
        giaoVien.setId(giaoVienDTO.getId());

        return giaoVienRepository.save(giaoVien);
    }

    @Override
    public Boolean delete(String id) {
        //check id giao vien
        Optional<GiaoVien> giaoVienOptional = giaoVienRepository.findById(id);
        if (!giaoVienOptional.isPresent()) {
            throw new RecordNotFoundException("giáo viên này không tồn tại");
        }
        giaoVienOptional.get().setBangCap(null);
        giaoVienOptional.get().setPhongBan(null);

        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(giaoVienOptional.get().getNguoiDung().getId());
        if (!nguoiDung.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy người dùng của gv");
        } else {
            giaoVienOptional.get().setNguoiDung(null);
        }
        List<GiaoVienLop> giaoVienLops = giaoVienLopRepository.findAllByGiaoVien_Id(id);

        for (GiaoVienLop hocSinhLop : giaoVienLops) {
            giaoVienLopRepository.delete(hocSinhLop);
        }
        giaoVienRepository.save(giaoVienOptional.get());
        nguoiDungRepository.delete(nguoiDung.get());
        giaoVienRepository.delete(giaoVienOptional.get());
        return true;
    }

    @Override
    public GiaoVien detail(String id) {
        Optional<GiaoVien> giaoVien = giaoVienRepository.findById(id);
        if (!giaoVien.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy gv");
        }
        return giaoVien.get();
    }
}
