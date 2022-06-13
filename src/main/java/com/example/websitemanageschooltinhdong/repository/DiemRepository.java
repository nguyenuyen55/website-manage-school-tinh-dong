package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.DiemMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiemRepository extends JpaRepository<DiemMonHoc,Integer> {

    List<DiemMonHoc> findAllByHocKiHocSinh_HocSinhId(String id);
//boi nam hoc
    @Query(value = "SELECT diem_mon_hoc.* FROM diem_mon_hoc\n" +
            "join hoc_ki_hoc_sinh on hoc_ki_hoc_sinh.id=diem_mon_hoc.hoc_ki_hoc_sinh_id\n" +
            "join hoc_ki on hoc_ki.id=hoc_ki_hoc_sinh.hoc_ki_id\n" +
            "where hoc_ki.nam_hoc_id = ?2 and hoc_ki_hoc_sinh.hoc_sinh_id = ?1",nativeQuery = true)
    List<DiemMonHoc> findAllByHocKiHocSinhandNamHoc_Id(@Param("hs") String idhs, @Param("nh") int idNamHoc);

    List<DiemMonHoc> findAllByHocKiHocSinh_Id(int id);

}
