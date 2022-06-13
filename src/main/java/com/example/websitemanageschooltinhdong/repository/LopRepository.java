package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LopRepository extends JpaRepository<Lop,Integer> {
    List<Lop> findAllByKhoi_Id(int id);
    List<Lop> findAllByKhoi_IdAndNamHoc_Id(int idkhoi,int id);
    @Query(value = "select * from lop\n" +
            "where lop.id not in(select thoi_khoa_bieu.lop_id FROM thoi_khoa_bieu) and lop.khoi_id = ?1 and nam_hoc_id = ?2",nativeQuery = true)
    List<Lop> findAllByKhoi_IdAndNamHoc_IdForThoiKhoaBieu(int idkhoi,int id);

}
