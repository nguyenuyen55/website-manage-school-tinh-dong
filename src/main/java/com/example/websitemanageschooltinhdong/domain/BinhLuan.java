package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class BinhLuan {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int idBaiGiang;
    @Column(columnDefinition = "TEXT")
    private String noiDung;
    private LocalDate time;
    private String username;

    @OneToMany(mappedBy = "binhLuan")
    @JsonIgnore
    List<TraLoiBinhLuan> traLoiBinhLuans;


}
