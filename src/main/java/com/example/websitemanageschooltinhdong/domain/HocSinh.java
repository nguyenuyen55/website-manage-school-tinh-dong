package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private NguoiDung nguoiDung;
    @OneToMany(mappedBy = "hocSinh")
    @JsonIgnore
    private List<HocKiHocSinh> hocKiHocSinh;
    //    @ManyToOne(fetch=FetchType.EAGER)
//    private Lop lop;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
//    @JoinTable(name = "movie_producer",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "producer_id"))
//    private List<Producer> producerList;
//    @OneToMany(mappedBy = "hocSinh")
//    @JsonIgnore
//    List<Diem> diems;
    @OneToMany(mappedBy = "hocSinh")
    @JsonIgnore
    List<HocSinhLop> hocSinhLops;
}
