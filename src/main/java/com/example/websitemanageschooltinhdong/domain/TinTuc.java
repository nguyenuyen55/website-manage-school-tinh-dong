package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TinTuc {
@Id
    private int id;
    private String tieuDe;
    private String noiDung;
    private String hinhAnh;

}
