package com.example.websitemanageschooltinhdong.service.impl;

import com.example.websitemanageschooltinhdong.domain.TinTuc;
import com.example.websitemanageschooltinhdong.repository.TinTucRepository;
import com.example.websitemanageschooltinhdong.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
