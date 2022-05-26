package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class PhongBan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ten;
    @OneToMany(mappedBy = "phongBan")
    @JsonIgnore
    List<GiaoVien> giaoViens;
}
