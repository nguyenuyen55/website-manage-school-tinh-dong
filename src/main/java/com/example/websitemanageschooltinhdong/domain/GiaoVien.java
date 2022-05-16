package com.example.websitemanageschooltinhdong.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GiaoVien {
@Id
    private String  id;
private String ten;
    private String diaChi;
    private String tenDaiHoc;
    private String ngaySinh;
    private String email;
    private String soDienThoai;
    private String gioiTinh;
    private String hinhAnh;
    //
    @ManyToOne
    private BangCap bangCap;
    @ManyToOne
    private PhongBan phongBan;
    @OneToOne(cascade = CascadeType.ALL)
    private NguoiDung nguoiDung;
    @OneToOne(cascade = CascadeType.ALL)
    private Lop lop;
}
