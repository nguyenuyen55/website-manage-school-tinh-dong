package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.Lop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LopRepository extends JpaRepository<Lop,Integer> {
    List<Lop> findAllByKhoi_Id(int id);
    List<Lop> findAllByKhoi_IdAndNamHoc_Id(int idkhoi,int id);
}
