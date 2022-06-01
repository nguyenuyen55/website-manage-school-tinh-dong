package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Khoi {
    @Id
    private int id;
    private String ten;
    @OneToMany(mappedBy = "khoi")
    @JsonIgnore
    List<Lop> lops;
    @OneToMany(mappedBy = "khoi")
    @JsonIgnore
    List<MonHoc> monHocs;


}
