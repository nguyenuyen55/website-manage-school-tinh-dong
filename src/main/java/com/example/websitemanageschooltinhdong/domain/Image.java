package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String linkimage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private TinTuc tintuc;
}
