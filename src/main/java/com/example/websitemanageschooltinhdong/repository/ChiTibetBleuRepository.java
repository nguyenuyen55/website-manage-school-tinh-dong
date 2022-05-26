package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.ChiTietThoiKhoaBieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChiTibetBleuRepository extends JpaRepository<ChiTietThoiKhoaBieu,Integer> {
    @Query(value = "SELECT chi_tiet_thoi_khoa_bieu.* FROM thoi_khoa_bieu tb\n" +
            "join chi_tiet_thoi_khoa_bieu on chi_tiet_thoi_khoa_bieu.thoi_khoa_bieu_id=tb.id\n" +
            "where tb.lop_id= :idlop",nativeQuery = true)
    List<ChiTietThoiKhoaBieu> findAllByIdLop(@Param("idlop") int idlop);

    List<ChiTietThoiKhoaBieu> findAllByThoiKhoaBieuLop_Id(int id);
}
