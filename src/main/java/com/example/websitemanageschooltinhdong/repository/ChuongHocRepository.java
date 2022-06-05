package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.ChuongHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ChuongHocRepository extends JpaRepository<ChuongHoc,Integer> {
List<ChuongHoc> findAllByMonHoc_Id(String id);
}
