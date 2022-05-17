package com.example.websitemanageschooltinhdong.repository;


import com.example.websitemanageschooltinhdong.domain.TinTuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TinTucRepository extends JpaRepository<TinTuc,Integer>{
    List<TinTuc> findAll();
    TinTuc findTinTucById(int id);
}
