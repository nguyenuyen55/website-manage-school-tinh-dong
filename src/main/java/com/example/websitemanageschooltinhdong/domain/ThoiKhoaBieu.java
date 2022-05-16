package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class ThoiKhoaBieu {
@Id
    private int id;
    @OneToOne(mappedBy = "thoiKhoaBieu")
    @JsonIgnore
    private Lop lop;
    @OneToMany(mappedBy = "thoiKhoaBieu")
    @JsonIgnore
    List<Thu> thus;


}
