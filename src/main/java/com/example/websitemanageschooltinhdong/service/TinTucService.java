package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.TinTuc;
import com.example.websitemanageschooltinhdong.dto.request.TinTucDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TinTucService {
    List<TinTuc> getAllTinTuc();
    TinTuc getTinTucById(int id);
    TinTuc create (TinTucDTO tintuc);
    TinTuc update (TinTucDTO tintuc);
    Boolean delete (int  id);
}
