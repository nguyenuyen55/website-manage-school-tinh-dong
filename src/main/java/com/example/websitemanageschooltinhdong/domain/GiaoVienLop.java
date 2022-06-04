package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GiaoVienLop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne
    @JoinColumn(name = "gv_id")
    GiaoVien giaoVien;

    @ManyToOne
    @JoinColumn(name = "Lopid")
    Lop lop;
    boolean active;
}