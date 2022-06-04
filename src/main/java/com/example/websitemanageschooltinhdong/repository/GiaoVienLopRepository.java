package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.GiaoVienLop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiaoVienLopRepository extends JpaRepository<GiaoVienLop,Integer> {
    GiaoVienLop findByGiaoVien_IdAndActiveTrue(String idgv);
    GiaoVienLop findByLop_IdAndActiveTrue(int idlop);
    List<GiaoVienLop> findAllByGiaoVien_Id(String id);
    List<GiaoVienLop> findAllByLop_Id(int id);
}
