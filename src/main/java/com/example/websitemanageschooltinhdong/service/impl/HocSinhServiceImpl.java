package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.domain.Lop;
import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.HocSinhRespository;
import com.example.websitemanageschooltinhdong.repository.LopRepository;
import com.example.websitemanageschooltinhdong.repository.NguoiDungRepository;
import com.example.websitemanageschooltinhdong.service.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HocSinhServiceImpl implements HocSinhService {
    @Autowired
    HocSinhRespository hocSinhRespository;
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    LopRepository lopRepository;
    private final PasswordEncoder passwordEncoder;

    public HocSinhServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<HocSinh> searchHocSinhByTen(String ten) {
        return hocSinhRespository.findAllByTenContaining(ten);
    }

    @Override
    public List<HocSinh> searchHocSinhByTenKhoi(String ten, int khoi) {
        return hocSinhRespository.findAllByTenContainingAndLop_Khoi_Id(ten, khoi);
    }

    @Override
    public List<HocSinh> searchHocSinhByTenKhoiLop(String ten, int lop, int khoi) {
        return hocSinhRespository.findAllByTenContainingAndLop_IdAndLop_Khoi_Id(ten, lop, khoi);
    }

    @Override
    public List<HocSinh> searchHocSinhAll() {
        return hocSinhRespository.findAll();
    }

    @Override
    public List<HocSinh> findAllIdLop(int id) {
        return hocSinhRespository.findAllByLopId(id);
    }

    @Override
    public HocSinh create(HocSinhDTO hocSinhDTO) {
//check id hoc sinh
        Optional<HocSinh> hocSinhCheck = hocSinhRespository.findById(hocSinhDTO.getId());
        if (hocSinhCheck.isPresent()) {
            throw new RecordNotFoundException("Học sinh này đã tồn tại");
        }
        //set lop
        Optional<Lop> lop = lopRepository.findById(hocSinhDTO.getIdLop());
        if (!lop.isPresent()) {
            throw new RecordNotFoundException("NOt found lớp");
        }
//set hoc sinh
        HocSinh hocSinh = HocSinh.builder()
                .id(hocSinhDTO.getId())
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
                .lop(lop.get())
                .build();
        HocSinh hocsinhreal = hocSinhRespository.save(hocSinh);
        //set nguoi nguoi cũng nhu tao nguoi dùng tendn, mk ,quyen,
        NguoiDung nguoiDung = NguoiDung.builder()
                .tenDangNhap("hs" + hocsinhreal.getId())
                .matKhau(passwordEncoder.encode("12345"))
                .quyen("ROLE_USER")
                .build();
        hocsinhreal.setNguoiDung(nguoiDung);
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
        Optional<Lop> lop = lopRepository.findById(hocSinhDTO.getIdLop());
        if (!lop.isPresent()) {
            throw new RecordNotFoundException("Not found lớp");
        }

        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(hocSinhDTO.getIdDung());
        if (!nguoiDung.isPresent()) {
            throw new RecordNotFoundException("Not found user");
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
                .lop(lop.get())
                .nguoiDung(nguoiDung.get())
                .build();

        return hocSinhRespository.save(hocSinh);
    }

    @Override
    public Boolean delete(String id) {
        //check id hoc sinh
        Optional<HocSinh> hocSinhCheck = hocSinhRespository.findById(id);
        if (!hocSinhCheck.isPresent()) {
            throw new RecordNotFoundException("không tìm thấy học sinh này");
        }
        hocSinhCheck.get().setLop(null);


        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(hocSinhCheck.get().getNguoiDung().getId());
        if (nguoiDung.isPresent()) {
            nguoiDungRepository.delete(nguoiDung.get());
        }
        hocSinhCheck.get().setNguoiDung(null);
        hocSinhRespository.save(hocSinhCheck.get());
        hocSinhRespository.deleteById(id);
        return true;
    }
}
