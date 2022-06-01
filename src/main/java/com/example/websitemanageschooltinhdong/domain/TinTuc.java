package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TinTuc {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tieuDe;
    @Column(columnDefinition = "TEXT")
    private String noiDung;
    private String hinhAnh;

}
