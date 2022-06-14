package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.Khoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KhoiRepository extends JpaRepository<Khoi,Integer> {
@Query(value = "SELECT khoi.id FROM khoi\n" +
        "join mon_hoc on mon_hoc.khoi_id=khoi.id\n" +
        "where mon_hoc.id= ?1",nativeQuery = true)
    Integer findIdKhoiByMonHocId(String idmon);
}
