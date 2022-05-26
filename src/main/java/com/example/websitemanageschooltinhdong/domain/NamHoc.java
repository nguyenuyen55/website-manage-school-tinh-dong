package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class NamHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int year;
    @OneToMany(mappedBy = "namHoc")
    @JsonIgnore
    List<HocKi> hocKis;

}
