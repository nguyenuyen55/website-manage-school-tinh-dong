package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ChiTietThoiKhoaBieu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String thu;
    private String thuTu;
    @ManyToOne
    MonHoc monHoc;
    @ManyToOne
    @JsonIgnore
    ThoiKhoaBieu thoiKhoaBieu;
}
