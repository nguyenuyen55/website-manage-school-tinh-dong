package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.GiaoVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GiaoVienRepository extends JpaRepository<GiaoVien,String> {
    List<GiaoVien> findAll();
    //tim kiem theo ten
    @Query(value = "SELECT * FROM giao_vien where giao_vien.ten like %:ten%",nativeQuery = true)
    List<GiaoVien> findAllByTenContaining(@Param("ten") String ten);
    // tim kiem theo te va ban
    @Query(value = "SELECT giao_vien.* FROM giao_vien \n" +
            "join phong_ban on phong_ban.id=giao_vien.phong_ban_id\n" +
            "where giao_vien.ten like %:ten% and phong_ban.id= :idBan",nativeQuery = true)
    List<GiaoVien> findAllByTenContainingAndPhongBan_Id(@Param("ten")String ten,@Param("idBan") String idban);
    // tim theo ban
    @Query(value = "SELECT giao_vien.* FROM giao_vien \n" +
            "join phong_ban on phong_ban.id=giao_vien.phong_ban_id\n" +
            "where phong_ban.id= :idBan",nativeQuery = true)
    List<GiaoVien> findAllByPhongBan_Id(@Param("idBan") String idban);

}
