package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class MonHoc {
    @Id
    private String id;
    private String ten;
    @OneToOne(cascade = CascadeType.ALL)
    private Diem diem;
    @OneToMany(mappedBy = "monHoc")
    @JsonIgnore
    List<BaiGiang> baiGiangs;
    @ManyToOne
    private Thu thu;
    @ManyToOne
    private Lop lopmh;
    @OneToMany(mappedBy = "monHoc")
    @JsonIgnore
    List<ChuongHoc> chuongHocs;
}
