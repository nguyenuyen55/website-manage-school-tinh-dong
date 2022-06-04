package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiaoVien {
    @Id
    @GeneratedValue(generator = "gv-generator")
    @GenericGenerator(name = "gv-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "GV"),
            strategy = "com.example.websitemanageschooltinhdong.domain.IdGenerator")
    private String id;
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
    @JsonIgnore
    private NguoiDung nguoiDung;
//    @OneToOne(cascade = CascadeType.ALL)
//    private Lop lopgv;
}
