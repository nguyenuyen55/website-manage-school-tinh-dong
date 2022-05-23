package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lop {
    @Id
    private int id;
    private String ten;
    @OneToOne(mappedBy = "lopgv")
    @JsonIgnore
    private GiaoVien giaoVien;
    @OneToMany(mappedBy = "lop")
    @JsonIgnore
    List<HocSinh> hocSinhs;
    @ManyToOne
    private Khoi khoi;
    @OneToOne(mappedBy = "lop")
    private ThoiKhoaBieu thoiKhoaBieu;
//    @OneToMany(mappedBy = "lopmh")
//    @JsonIgnore
//    List<MonHoc> monHocs;
}
