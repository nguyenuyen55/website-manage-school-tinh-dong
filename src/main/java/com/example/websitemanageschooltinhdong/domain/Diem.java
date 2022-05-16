package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Diem {
    @Id
    private int id;
    private double giaTriDiem;
    @ManyToOne
    private HocSinh hocSinh;
    @OneToOne(mappedBy = "diem")
    @JsonIgnore
    private MonHoc monHoc;
}
