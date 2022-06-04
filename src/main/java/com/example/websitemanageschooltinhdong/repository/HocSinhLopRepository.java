package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.HocSinhLop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HocSinhLopRepository extends JpaRepository<HocSinhLop,Integer> {
    List<HocSinhLop> findAllByHocSinh_Id(String id);
    List<HocSinhLop> findAllByLop_IdAndActiveTrue(int id);


}
