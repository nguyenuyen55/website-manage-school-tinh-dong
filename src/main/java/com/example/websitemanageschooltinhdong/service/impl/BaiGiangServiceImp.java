package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import com.example.websitemanageschooltinhdong.domain.ChuongHoc;
import com.example.websitemanageschooltinhdong.dto.request.BaiGiangDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.BaiGangRepository;
import com.example.websitemanageschooltinhdong.repository.ChuongHocRepository;
import com.example.websitemanageschooltinhdong.service.BaiGiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BaiGiangServiceImp implements BaiGiangService {
    @Autowired
    BaiGangRepository baiGiangResposity;
    @Autowired
    ChuongHocRepository chuongHocRepository;

    @Override
    public BaiGiang findById(int id) {

        return baiGiangResposity.findById(id);
    }

    @Override
    public BaiGiang createBaiGiang(BaiGiangDTO baiGiangDTO) {
        Optional<ChuongHoc> chuongHoc = chuongHocRepository.findById(baiGiangDTO.getIdChuong());
        BaiGiang baiGiang = new BaiGiang();
        if (chuongHoc.isPresent()) {
            baiGiang.setChuongHoc(chuongHoc.get());
        } else {
            throw new RecordNotFoundException("chuong hoc khong tim thay ");
        }
        baiGiang.setId(baiGiangDTO.getId());
        baiGiang.setFileTaiLieu(baiGiangDTO.getFileTaiLieu());
        baiGiang.setFileVideo(baiGiangDTO.getFileVideo());
        baiGiang.setTen(baiGiangDTO.getTen());
        baiGiang.setMota(baiGiangDTO.getMota());
        return baiGiangResposity.save(baiGiang);
    }

    @Override
    public BaiGiang updateBaiGiang(BaiGiangDTO baiGiangDTO) {
        Optional<ChuongHoc> chuongHoc = chuongHocRepository.findById(baiGiangDTO.getIdChuong());
        BaiGiang baiGiangReal = baiGiangResposity.findById(baiGiangDTO.getId());

        BaiGiang baiGiang = new BaiGiang();
        if (baiGiangReal != null) {
            baiGiang.setId(baiGiangDTO.getId());
        } else {
            throw new RecordNotFoundException("bai giang khong tim thay");
        }
        if (chuongHoc.isPresent()) {
            baiGiang.setChuongHoc(chuongHoc.get());
        } else {
            throw new RecordNotFoundException("chuong hoc khong tim thay ");
        }
        baiGiang.setFileTaiLieu(baiGiangDTO.getFileTaiLieu());
        baiGiang.setFileVideo(baiGiangDTO.getFileVideo());
        baiGiang.setTen(baiGiangDTO.getTen());
        baiGiang.setMota(baiGiangDTO.getMota());
        return baiGiangResposity.save(baiGiang);
    }

    @Override
    public Boolean deleteBaiGiang(int id) {
        BaiGiang baiGiangReal = baiGiangResposity.findById(id);
        if (baiGiangReal == null) {
            throw new RecordNotFoundException("bai giang khong tim thay");
        }
        baiGiangResposity.delete(baiGiangReal);
        return true;
    }

    @Override
    public List<BaiGiang> getall() {
        return baiGiangResposity.findAll();
    }

    @Override
    public List<BaiGiang> getAllByMonHocAndTenChuongHoc(Integer idchuong, String idmon, String name) {
        if (idchuong == null &&name != null && idmon != null ) {
            return baiGiangResposity.findAllByChuongHoc_MonHoc_IdAndTenContaining(idmon, name);

        }
        if (idchuong == null && idmon == null &&name!=null){
            return baiGiangResposity.findAllByTenContaining( name);
        }

        if(idchuong!=null && idmon!=null && name==null){
            return baiGiangResposity.findAllByChuongHoc_IdAndChuongHoc_MonHoc_Id(idchuong,idmon);
        }
            return baiGiangResposity.findAllByChuongHoc_IdAndChuongHoc_MonHoc_IdAndTenContaining(idchuong, idmon, name);
    }

}
