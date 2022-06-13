package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class MonHoc {
    @Id
    private String id;
    private String ten;
    //    @OneToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Diem diem;
//    @OneToMany(mappedBy = "monHoc")
//        @JsonIgnore
//    List<BaiGiang> baiGiangs;
    @ManyToOne
    @JsonIgnore
    private Khoi khoi;
    @OneToMany(mappedBy = "monHoc")
    @JsonBackReference
    List<ChuongHoc> chuongHocs;
    @OneToMany(mappedBy = "monHoc")
    @JsonBackReference
    List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieu;
    @OneToMany(mappedBy = "monHoc")
    @JsonBackReference
    List<DiemMonHoc> diemMonHocs;
}
