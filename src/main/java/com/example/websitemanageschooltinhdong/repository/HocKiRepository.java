package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.HocKi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HocKiRepository extends JpaRepository<HocKi,Integer> {
    List<HocKi> findAllByNamHocYear(int year);
}
