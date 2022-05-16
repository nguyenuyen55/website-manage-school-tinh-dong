package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HocSinh {
    @Id
    private String id;
    private String ten;
    private String diaChi;
    private String gioiTinh;
    private String trangThai;
    private String danToc;
    private String tenBo;
    private String tenMe;
    private String tonGiao;
    private String sdtBoMe;
    private String hinhAnh;
    @OneToOne(cascade = CascadeType.ALL)
    private NguoiDung nguoiDung;
    @ManyToOne
    private Lop lop;
    @OneToMany(mappedBy = "hocSinh")
    @JsonIgnore
    List<Diem> diems;

}
