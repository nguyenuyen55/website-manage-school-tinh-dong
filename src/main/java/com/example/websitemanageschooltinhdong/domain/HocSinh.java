package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HocSinh {
    @Id
    @GeneratedValue(generator = "hs-generator")
    @GenericGenerator(name = "hs-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "HS"),
            strategy = "com.example.websitemanageschooltinhdong.domain.IdGenerator")
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
    private Boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    private NguoiDung nguoiDung;
    @OneToOne(mappedBy = "hocSinh")
    @JsonIgnore
    private HocKiHocSinh hocKiHocSinh;
    @ManyToOne(fetch=FetchType.EAGER)
    private Lop lop;
//    @OneToMany(mappedBy = "hocSinh")
//    @JsonIgnore
//    List<Diem> diems;

}
