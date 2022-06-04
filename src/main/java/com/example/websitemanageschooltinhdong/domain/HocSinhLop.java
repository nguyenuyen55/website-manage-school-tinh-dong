package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class HocSinhLop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    HocSinh hocSinh;

    @ManyToOne
    @JoinColumn(name = "Lopid")
    Lop lop;
    boolean active;
}
