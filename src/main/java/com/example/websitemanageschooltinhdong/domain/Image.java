package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String linkimage;
    @ManyToOne
    @JsonBackReference
    private TinTuc tintuc;
}
