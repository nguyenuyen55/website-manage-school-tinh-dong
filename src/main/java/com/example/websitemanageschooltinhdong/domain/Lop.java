package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ten;
    @ManyToOne
    private Khoi khoi;
    @OneToOne(mappedBy = "lop")
    @JsonIgnore
    private ThoiKhoaBieu thoiKhoaBieu;
    @ManyToOne
    private NamHoc namHoc;
    @OneToMany(mappedBy = "lop")
    @JsonIgnore
    List<HocSinhLop> hocSinhLops;
}
