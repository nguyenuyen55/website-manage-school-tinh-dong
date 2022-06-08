package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class TinTuc {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tieuDe;
    @Column(columnDefinition = "TEXT")
    private String noiDung;
    @OneToMany(mappedBy = "tintuc")
    private List<Image> images;

}
