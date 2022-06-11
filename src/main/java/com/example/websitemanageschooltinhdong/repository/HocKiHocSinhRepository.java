package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.HocKiHocSinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HocKiHocSinhRepository extends JpaRepository<HocKiHocSinh,Integer> {
    @Query("select b from HocKiHocSinh b where b.hocSinh.id = ?1 ")
    List<HocKiHocSinh> findAllByHocSinh_Id(String id);
}
