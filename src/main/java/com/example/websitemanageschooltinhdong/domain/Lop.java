package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lop {
    @Id
    private int id;
    private String ten;
    @OneToOne(mappedBy = "lop")
    @JsonIgnore
    private GiaoVien giaoVien;
    @OneToMany(mappedBy = "lop")
    @JsonIgnore
    List<HocSinh> hocSinhs;
    @ManyToOne
    private Khoi khoi;
    @OneToOne(cascade = CascadeType.ALL)
    private ThoiKhoaBieu thoiKhoaBieu;

    @OneToMany(mappedBy = "lop")
    @JsonIgnore
    List<MonHoc> monHocs;
}
