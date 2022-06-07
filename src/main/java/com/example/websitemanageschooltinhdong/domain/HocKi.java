package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HocKi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "hocKi")
    @JsonIgnore
    private List<HocKiHocSinh> hocKiHocSinhs;
    @ManyToOne
    @JsonIgnore
    private NamHoc namHoc;

}
