package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.domain.HocSinhLop;
import com.example.websitemanageschooltinhdong.domain.Lop;
import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.HocSinhLopRepository;
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
    HocSinhLopRepository hocSinhLopRepository;
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
    public List<HocSinh> findALL() {
        List<HocSinh> findAll = hocSinhRespository.findAll();
        if(findAll.size()==0){
           throw new RecordNotFoundException("Không tìm thấy sinh viên nào");
        }
        return findAll;
    }

    @Override
    public List<HocSinh> findAllIdLop(int id) {

//        return hocSinhRespository.findAllByLopId(id);
        return hocSinhRespository.findAllByHocSinhLops(id);
    }

    @Override
    public HocSinh create(HocSinhDTO hocSinhDTO) {



        Optional<Lop> lop = lopRepository.findById(hocSinhDTO.getIdLop());
        if (!lop.isPresent()) {
            throw new RecordNotFoundException("NOt found lớp");
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
                .build();

        HocSinh hocsinhreal = hocSinhRespository.save(hocSinh);

        //set nguoi nguoi cũng nhu tao nguoi dùng tendn, mk ,quyen,
        NguoiDung nguoiDung = NguoiDung.builder()
                .tenDangNhap( hocsinhreal.getId())
                .matKhau(passwordEncoder.encode("hs123"))
                .quyen("ROLE_USER")
                .build();
        hocsinhreal.setNguoiDung(nguoiDung);
        //save một đối tượng chứa id lop và id hoc sinh
//        List<HocSinhLop> lophss = hocSinhLopRepository.findAllByLop_Id(lop.get().getId());
//
//        if (lophss.size() != 0) {
//            for (HocSinhLop hocSinhLop : lophss) {
//                hocSinhLop.setActive(false);
//                hocSinhLopRepository.save(hocSinhLop);
//            }
//        }

//        tạo mới một đối tượng giáo viên lóp mới


        HocSinhLop hocSinhLop = new HocSinhLop();
        hocSinhLop.setHocSinh(hocSinhRespository.save(hocsinhreal));
        hocSinhLop.setLop(lop.get());
        hocSinhLop.setActive(true);
        hocSinhLopRepository.save(hocSinhLop);
        return hocSinhRespository.save(hocsinhreal) ;
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
                .nguoiDung(nguoiDung.get())
                .build();

        return hocSinhRespository.save(hocSinh);
    }

    @Override
    public HocSinh detailById(String id) {
        Optional<HocSinh> hocSinh = hocSinhRespository.findById(id);
        if(!hocSinh.isPresent()){
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



        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findById(hocSinhCheck.get().getNguoiDung().getId());
        if (nguoiDung.isPresent()) {
            hocSinhCheck.get().setNguoiDung(null);

        }
        List<HocSinhLop> hocSinhLops = hocSinhLopRepository.findAllByHocSinh_Id(id);

        for (HocSinhLop hocSinhLop :hocSinhLops){
            hocSinhLopRepository.delete(hocSinhLop);
        }
        hocSinhRespository.save(hocSinhCheck.get());
        nguoiDungRepository.delete(nguoiDung.get());
        hocSinhRespository.deleteById(id);
        return true;
    }
}
