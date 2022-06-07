package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.dto.request.NguoiDungDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung,Integer> {
//@Query(value = "SELECT * FROM nguoi_dung\n" +
//        "where nguoi_dung.ten_dang_nhap = ?1",nativeQuery = true)
@Query("select new com.example.websitemanageschooltinhdong.dto.request.NguoiDungDTO(n.id,n.tenDangNhap,n.matKhau,n.quyen) from NguoiDung n where n.tenDangNhap = ?1 ")
NguoiDungDTO findByTenDangNhapDTO(String ten);
    NguoiDung findByTenDangNhap( String ten);
    @Query(value = "SELECT * FROM nguoi_dung\n" +
            "where nguoi_dung.ten_dang_nhap = :ten",nativeQuery = true)
    Optional<NguoiDung> findByTenDangNhapOptinal(@Param("ten") String ten);

}
