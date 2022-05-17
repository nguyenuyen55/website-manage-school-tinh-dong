package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.TinTuc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TinTucService {
    List<TinTuc> getAllTinTuc();
    TinTuc getTinTucById(int id);
}
