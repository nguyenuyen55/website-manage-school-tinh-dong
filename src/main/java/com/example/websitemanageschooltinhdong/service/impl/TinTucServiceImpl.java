package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.Image;
import com.example.websitemanageschooltinhdong.domain.TinTuc;
import com.example.websitemanageschooltinhdong.dto.request.ImageDTO;
import com.example.websitemanageschooltinhdong.dto.request.TinTucDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.ImageRepository;
import com.example.websitemanageschooltinhdong.repository.TinTucRepository;
import com.example.websitemanageschooltinhdong.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TinTucServiceImpl implements TinTucService {
    @Autowired
    TinTucRepository tinTucRepository;
    @Autowired
    ImageRepository imageRepository;

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
        tinTuc.setNoiDung(tinTucDTO.getNoiDung());
        TinTuc tinTucSave = tinTucRepository.save(tinTuc);

        for (ImageDTO imageDTO : tinTucDTO.getImageList()) {
            Image image = new Image();
            image.setTintuc(tinTucSave);
            image.setLinkimage(imageDTO.getLink());
            imageRepository.save(image);
        }

        return tinTucRepository.save(tinTuc);
    }

    @Override
    public TinTuc update(TinTucDTO tinTucDTO) {
        TinTuc tinTucRel = new TinTuc();
        tinTucRel.setId(tinTucDTO.getId());
        tinTucRel.setTieuDe(tinTucDTO.getTieuDe());
        tinTucRel.setNoiDung(tinTucDTO.getNoiDung());

        List<Image> images = imageRepository.findAllByTintuc_Id(tinTucDTO.getId());
        for (Image image : images) {
            imageRepository.delete(image);
        }
        for (ImageDTO imageDTO : tinTucDTO.getImageList()) {
            Image image = new Image();
            image.setTintuc(tinTucRepository.save(tinTucRel));
            image.setLinkimage(imageDTO.getLink());
            imageRepository.save(image);
        }
        return tinTucRepository.save(tinTucRel);
    }

    @Override
    public Boolean delete(int id) {
        Optional<TinTuc> tinTuc = tinTucRepository.findById(id);
        if (!tinTuc.isPresent()) {
            throw new RecordNotFoundException("tin tu khong tim thay");
        }
        List<Image> images = imageRepository.findAllByTintuc_Id(id);
        for (Image image : images) {
            imageRepository.delete(image);
        }
        tinTucRepository.delete(tinTuc.get());
        return true;
    }
}
