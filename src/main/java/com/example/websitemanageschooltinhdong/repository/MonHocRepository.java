package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonHocRepository extends JpaRepository<MonHoc,String> {
    @Query(value = "SELECT * FROM mon_hoc where khoi_id= ?1",nativeQuery = true)
List<MonHoc> findAllByKhoiId(int id);
}
