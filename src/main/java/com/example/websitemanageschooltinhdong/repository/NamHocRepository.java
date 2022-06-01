package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.NamHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NamHocRepository extends JpaRepository<NamHoc,Integer> {
    NamHoc findByYear(int year);
}
