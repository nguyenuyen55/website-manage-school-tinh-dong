package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JsonIgnore
    private ThoiKhoaBieu thoiKhoaBieu;
//    @OneToMany(mappedBy = "lopmh")
//    @JsonIgnore
//    List<MonHoc> monHocs;
}
