package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CauHoi {
    @Id
    private int id;
    private String noiDung;

}
