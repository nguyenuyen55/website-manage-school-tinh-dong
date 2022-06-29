package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.dto.response.HocSinhReponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HocSinhRespository extends JpaRepository<HocSinh, String> {
    List<HocSinh> findAll();
    //theo ten

    @Query(value = "SELECT * FROM hoc_sinh where hoc_sinh.ten like %:ten%", nativeQuery = true)
    List<HocSinh> findAllByTenContaining(@Param("ten") String ten);


    @Query(value = "SELECT hs.* FROM hoc_sinh hs\n" +
            "join hoc_sinh_lop hsl on hs.id = hsl.hoc_sinh_id\n" +
            "join lop on lop.id = hsl.lopid\n" +
            "where lop.khoi_id= :idKhoi and hs.ten like %:ten% ", nativeQuery = true)
    List<HocSinh> findAllByTenContainingAndLop_Khoi_Id(@Param("ten") String ten, @Param
            ("idKhoi") int idKhoi);

    //    //ket hop cả ba
    @Query(value = "SELECT hs.* FROM hoc_sinh hs\n" +
            "join hoc_sinh_lop hsl on hs.id = hsl.hoc_sinh_id\n" +
            "join lop on lop.id = hsl.lopid\n" +
            "where lop.khoi_id= :idKhoi and hs.ten like %:ten% and hsl.lopid= :idlop", nativeQuery = true)
    List<HocSinh> findAllByTenContainingAndLop_IdAndLop_Khoi_Id(@Param("ten") String ten, @Param("idlop") int idLop, @Param("idKhoi") int idKhoi);


    //get hs by id teacher
    @Query(value = "SELECT hs.* FROM hoc_sinh hs\n" +
            "join hoc_sinh_lop hsl on hs.id = hsl.hoc_sinh_id\n" +
            "where hsl.lopid= :id and hsl.active=true", nativeQuery = true)
    List<HocSinh> findAllByHocSinhLops(@Param("id") int id);

@Query(value = "SELECT hoc_sinh.* FROM hoc_sinh\n" +
        "join hoc_sinh_lop on hoc_sinh_lop.hoc_sinh_id= hoc_sinh.id\n" +
        "where hoc_sinh_lop.active=true and hoc_sinh_lop.lopid = :idlop ",nativeQuery = true)
List<HocSinh> findAllByHocSinhLopsAdmin(@Param("idlop") int idlop);
    @Query(value = "SELECT * FROM hoc_sinh where hoc_sinh.id like %:id%", nativeQuery = true)
List<HocSinh> findAllByIdContaining(@Param("id") String id);
}
