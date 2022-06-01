package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class NamHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int year;
    @OneToMany(mappedBy = "namHoc")
    @JsonIgnore
    List<HocKi> hocKis;
    @OneToMany(mappedBy = "namHoc")
    @JsonIgnore
    List<Lop> lops;
}
