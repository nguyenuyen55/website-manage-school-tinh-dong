package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ThoiKhoaBieu {
@Id
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private Lop lop;
    @OneToMany(mappedBy = "thoiKhoaBieu")
    List<ChiTietThoiKhoaBieu> chiTietThoiKhoaBieus;


}
