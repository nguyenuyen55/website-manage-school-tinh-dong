package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HocKiHocSinh {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JsonBackReference
    private HocKi hocKi;
    @ManyToOne
    @JsonBackReference
    private HocSinh hocSinh;
    @OneToMany(mappedBy = "hocKiHocSinh")
    @JsonIgnore
    List<DiemMonHoc> diemMonHocs;

}
