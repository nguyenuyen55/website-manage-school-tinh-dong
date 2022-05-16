package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Khoi {
    @Id
    private int id;
    private String ten;
    @OneToMany(mappedBy = "khoi")
    @JsonIgnore
    List<Lop> lops;


}
