package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class BinhLuan {
@Id
    private int id;
    private String idBaiGiang;
    private String noiDung;
    private LocalDate time;
    private int idNguoiDung;

    @OneToMany(mappedBy = "binhLuan")
    @JsonIgnore
    List<TraLoiBinhLuan> traLoiBinhLuans;


}
