package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class CauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String noiDung;
    @NotNull
    private String hoTen;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String tieuDe;
    @ManyToOne
    ChuDe chuDe;


}
