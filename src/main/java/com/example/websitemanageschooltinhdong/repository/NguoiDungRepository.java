package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung,Integer> {
@Query(value = "SELECT * FROM nguoi_dung\n" +
        "where nguoi_dung.ten_dang_nhap = :ten",nativeQuery = true)
    NguoiDung findByTenDangNhap(@Param("ten") String ten);
    @Query(value = "SELECT * FROM nguoi_dung\n" +
            "where nguoi_dung.ten_dang_nhap = :ten",nativeQuery = true)
    Optional<NguoiDung> findByTenDangNhapOptinal(@Param("ten") String ten);

}
