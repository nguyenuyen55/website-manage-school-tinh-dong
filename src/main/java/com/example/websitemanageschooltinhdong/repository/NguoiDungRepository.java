package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung,Integer> {
    NguoiDung findByTenDangNhap(String ten);
}
