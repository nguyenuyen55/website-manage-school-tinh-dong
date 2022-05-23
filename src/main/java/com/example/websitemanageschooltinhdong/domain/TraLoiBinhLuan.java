package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class TraLoiBinhLuan {
    @Id
    private int id;
    private String noiDung;
    private LocalDate thoigian;
    @ManyToOne
    private BinhLuan binhLuan;
}
