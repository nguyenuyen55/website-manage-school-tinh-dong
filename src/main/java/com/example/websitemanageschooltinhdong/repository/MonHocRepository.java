package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonHocRepository extends JpaRepository<MonHoc,String> {
List<MonHoc> findAllByKhoiId(int id);
}
