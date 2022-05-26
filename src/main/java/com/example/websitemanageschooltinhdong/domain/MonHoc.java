package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
//    @ManyToOne
//    @JsonIgnore
//    @JsonBackReference
//    private Lop lopmh;
    @OneToMany(mappedBy = "monHoc")
        @JsonBackReference
    List<ChuongHoc> chuongHocs;
    @OneToMany(mappedBy = "monHoc")
            @JsonIgnore
    List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieu;
    @OneToMany(mappedBy = "monHoc")
    @JsonIgnore
    List<DiemMonHoc> diemMonHocs;

}
