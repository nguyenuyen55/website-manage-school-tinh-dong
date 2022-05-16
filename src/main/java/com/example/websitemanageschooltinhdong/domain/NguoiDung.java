package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NguoiDung {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String quyen;
    @OneToOne(mappedBy = "nguoiDung")
    @JsonIgnore
    private GiaoVien giaoVien;
    @OneToOne(mappedBy = "nguoiDung")
    @JsonIgnore
    private HocSinh hocSinh;

}
