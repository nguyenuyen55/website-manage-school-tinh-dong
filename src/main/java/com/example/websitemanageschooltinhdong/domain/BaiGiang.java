package com.example.websitemanageschooltinhdong.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiGiang {
    @Id
    private int id;
    private String ten;
    private String mota;
    private String fileVideo;
    private String fileTaiLieu;
    @ManyToOne
    private MonHoc monHoc;
    @ManyToOne
    private ChuongHoc chuongHoc;

}
