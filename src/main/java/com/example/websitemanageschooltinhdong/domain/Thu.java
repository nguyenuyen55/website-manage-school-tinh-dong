package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Thu {
    @Id
    private int id;
    private String tenThu;
    @OneToMany(mappedBy = "thu")
    @JsonIgnore
    List<MonHoc> monHocs;
    @ManyToOne
    private ThoiKhoaBieu thoiKhoaBieu;
}
