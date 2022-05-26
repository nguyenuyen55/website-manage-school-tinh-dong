package com.example.websitemanageschooltinhdong.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaiGiang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ten;
    private String mota;
    private String fileVideo;
    private String fileTaiLieu;
//   @Lob
//    private byte[] dataVideo;
//    @Lob
//    private byte[] datafile;
//    @ManyToOne
//    private MonHoc monHoc;
    @ManyToOne
    private ChuongHoc chuongHoc;

}
