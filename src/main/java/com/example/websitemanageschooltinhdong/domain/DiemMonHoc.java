package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
    @Value("0")
    private double diemGiuaKy;
    @Value("0")
    private double diemCuoiKy;
}
