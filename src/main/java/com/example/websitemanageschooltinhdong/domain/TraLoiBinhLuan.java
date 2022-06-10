package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class TraLoiBinhLuan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String noiDung;
    private LocalDate thoigian;
    @ManyToOne
    private BinhLuan binhLuan;
}
