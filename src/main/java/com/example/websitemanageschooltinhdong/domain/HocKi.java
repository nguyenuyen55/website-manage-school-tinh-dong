package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class HocKi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToOne(mappedBy = "hocKi")
    @JsonIgnore
    private HocKiHocSinh hocKiHocSinh;
    @ManyToOne
    private NamHoc namHoc;

}
