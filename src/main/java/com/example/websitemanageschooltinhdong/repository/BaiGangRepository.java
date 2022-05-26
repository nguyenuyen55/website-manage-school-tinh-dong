package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.BaiGiang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface BaiGangRepository extends JpaRepository<BaiGiang,Integer> {
BaiGiang findById(int id);
}
