package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDung {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String quyen;
    @OneToOne(mappedBy = "nguoiDung",cascade = CascadeType.ALL)
    @JsonBackReference
    private GiaoVien giaoVien;
    @OneToOne(mappedBy = "nguoiDung",cascade = CascadeType.ALL)
    @JsonBackReference
    private HocSinh hocSinh;
}
