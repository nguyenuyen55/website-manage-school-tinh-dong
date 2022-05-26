package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class DiemMonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private MonHoc monHoc;
    @ManyToOne
    private HocKiHocSinh hocKiHocSinh;
    private double diemGiuaKy;
    private double diemCuoiKy;
}
