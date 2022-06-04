package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.TinTuc;
import com.example.websitemanageschooltinhdong.dto.request.TinTucDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.TinTucRepository;
import com.example.websitemanageschooltinhdong.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TinTucServiceImpl implements TinTucService {
    @Autowired
    TinTucRepository tinTucRepository;
    @Override
    public List<TinTuc> getAllTinTuc() {
        return tinTucRepository.findAll();
    }

    @Override
    public TinTuc getTinTucById(int id) {
        return tinTucRepository.findTinTucById(id);
    }

    @Override
    public TinTuc create(TinTucDTO tinTucDTO) {
        TinTuc tinTuc = new TinTuc();
        tinTuc.setTieuDe(tinTucDTO.getTieuDe());
        tinTuc.setHinhAnh(tinTucDTO.getHinhAnh());
        tinTuc.setNoiDung(tinTucDTO.getNoiDung());
        return tinTucRepository.save(tinTuc);
    }

    @Override
    public TinTuc update(TinTuc tintuc) {
        Optional<TinTuc> tinTuc =tinTucRepository.findById(tintuc.getId());
        if(!tinTuc.isPresent()){
            throw new RecordNotFoundException("tin tu khong tim thay");
        }
        return tinTucRepository.save(tintuc);
    }

    @Override
    public Boolean delete(int id) {
        Optional<TinTuc> tinTuc =tinTucRepository.findById(id);
        if(!tinTuc.isPresent()){
            throw new RecordNotFoundException("tin tu khong tim thay");
        }
        tinTucRepository.delete(tinTuc.get());
        return true ;
    }
}
