package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.TraLoiBinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraloiBinhLuanRepository extends JpaRepository<TraLoiBinhLuan,Integer> {
    List<TraLoiBinhLuan> findAllByBinhLuan_Id(int id);
}
