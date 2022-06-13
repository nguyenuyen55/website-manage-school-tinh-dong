package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class ChuongHoc {
    @Id
    private int id;
    private String ten;
    @OneToMany(mappedBy = "chuongHoc")
    @JsonIgnore
    List<BaiGiang> baiGiangs;
    @ManyToOne
    private MonHoc monHoc;
}
