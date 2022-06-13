package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ThoiKhoaBieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Lop lop;
    @OneToMany(mappedBy = "thoiKhoaBieu")
    @JsonBackReference
    List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieus;


}
