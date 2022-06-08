package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public interface BaiGangRepository extends JpaRepository<BaiGiang,Integer> {
BaiGiang findById(int id);
    @Query(value = "SELECT bai_giang.* FROM bai_giang\n" +
            "join chuong_hoc on chuong_hoc.id=bai_giang.chuong_hoc_id\n" +
            "where chuong_hoc.mon_hoc_id = ?2 and bai_giang.ten like %?3% and chuong_hoc.id = ?1",nativeQuery = true)
List<BaiGiang> findAllByChuongHoc_IdAndChuongHoc_MonHoc_IdAndTenContaining(int idchuong ,String idmon,String name);
@Query(value = "SELECT bai_giang.* FROM bai_giang\n" +
        "join chuong_hoc on chuong_hoc.id=bai_giang.chuong_hoc_id\n" +
        "where chuong_hoc.mon_hoc_id = ?1 and bai_giang.ten like %?2%",nativeQuery = true)
List<BaiGiang> findAllByChuongHoc_MonHoc_IdAndTenContaining(String idmon,String name);
    @Query(value = "SELECT bai_giang.* FROM bai_giang\n" +
            "join chuong_hoc on chuong_hoc.id=bai_giang.chuong_hoc_id\n" +
            "where chuong_hoc.mon_hoc_id = ?2 and chuong_hoc.id = ?1",nativeQuery = true)
    List<BaiGiang> findAllByChuongHoc_IdAndChuongHoc_MonHoc_Id(int idchuong ,String idmon);
List<BaiGiang> findAllByTenContaining(String name);
}
