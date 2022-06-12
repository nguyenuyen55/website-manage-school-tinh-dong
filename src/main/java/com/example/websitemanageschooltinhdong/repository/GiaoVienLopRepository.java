package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.GiaoVienLop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiaoVienLopRepository extends JpaRepository<GiaoVienLop,Integer> {
    GiaoVienLop findByGiaoVien_IdAndActiveTrue(String idgv);
    @Query("select l from GiaoVienLop l where l.lop.id = ?1 and l.active=true ")
    GiaoVienLop findByLop_IdAndActiveTrue(int idlop);
    List<GiaoVienLop> findAllByLop_IdAndActiveTrue(int idlop);
    List<GiaoVienLop> findAllByGiaoVien_Id(String id);

}
