package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class HocKiHocSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private HocKi hocKi;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private HocSinh hocSinh;
    @OneToMany(mappedBy = "hocKiHocSinh")
    @JsonIgnore
    List<DiemMonHoc> diemMonHocs;

}
