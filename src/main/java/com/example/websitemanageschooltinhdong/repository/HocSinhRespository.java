package com.example.websitemanageschooltinhdong.repository;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HocSinhRespository extends JpaRepository<HocSinh,String> {
List<HocSinh> findAll();
    //theo ten

    @Query(value = "SELECT * FROM hoc_sinh where hoc_sinh.ten like %:ten%",nativeQuery = true)
    List<HocSinh> findAllByTenContaining(@Param("ten") String ten);
//    //theo tên và theo khối
    @Query(value = "SELECT hoc_sinh.* FROM hoc_sinh\n" +
            "join lop on lop.id= hoc_sinh.lop_id\n" +
            "join khoi on lop.id= khoi.id \n" +
            "where hoc_sinh.ten like %:ten% and khoi.id= :idKhoi ",nativeQuery = true)
    List<HocSinh>  findAllByTenContainingAndLop_Khoi_Id(@Param("ten") String ten, @Param("idKhoi")int idKhoi);

//    //ket hop cả ba
@Query(value = "SELECT hoc_sinh.* FROM hoc_sinh\n" +
        "join lop on lop.id= hoc_sinh.lop_id\n" +
        "join khoi on lop.id= khoi.id \n" +
        "where hoc_sinh.ten like %:ten% and khoi.id= :idKhoi and lop.id= :idlop",nativeQuery = true)
 List<HocSinh> findAllByTenContainingAndLop_IdAndLop_Khoi_Id(@Param("ten")String ten, @Param("idlop")int idLop, @Param("idKhoi") int idKhoi);

}
