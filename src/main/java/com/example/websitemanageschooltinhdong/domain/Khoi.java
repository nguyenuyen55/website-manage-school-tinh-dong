package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Khoi {
    @Id
    private int id;
    private String ten;
    @OneToMany(mappedBy = "khoi")
    @JsonIgnore
    @JsonManagedReference
    List<Lop> lops;
    @OneToMany(mappedBy = "khoi")
    @JsonIgnore
    @JsonManagedReference
    List<MonHoc> monHocs;


}
