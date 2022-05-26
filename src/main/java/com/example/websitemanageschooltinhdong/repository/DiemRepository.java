package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.DiemMonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiemRepository extends JpaRepository<DiemMonHoc,Integer> {

    List<DiemMonHoc> findAllByHocKiHocSinh_HocSinhId(String id);
//boi nam hoc
    @Query(value = "SELECT dmh.* FROM diem_mon_hoc dmh\n" +
            "join hoc_ki_hoc_sinh hkhs on hkhs.hoc_ki_id = dmh.hoc_ki_hoc_sinh_id\n" +
            "join hoc_ki hk on hkhs.hoc_ki_id = hk.id\n" +
            "join nam_hoc nh on nh.id= hk.nam_hoc_id\n" +
            "where  hkhs.hoc_sinh_id= :hs and nh.id= :nh",nativeQuery = true)
    List<DiemMonHoc> findAllByHocKiHocSinh_HocSinhIdAndHocKiHocSinh_HocKi_NamHoc_Id(@Param("hs") String idhs, @Param("nh") int idNamHoc);

    

}
