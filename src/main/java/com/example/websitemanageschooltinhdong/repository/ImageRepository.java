package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> findAllByTintuc_Id(int id);
}
