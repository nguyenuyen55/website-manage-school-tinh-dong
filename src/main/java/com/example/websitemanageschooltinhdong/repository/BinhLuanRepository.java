package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.BinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinhLuanRepository extends JpaRepository<BinhLuan,Integer> {

    List<BinhLuan> findAllByIdBaiGiang(int id);
}
