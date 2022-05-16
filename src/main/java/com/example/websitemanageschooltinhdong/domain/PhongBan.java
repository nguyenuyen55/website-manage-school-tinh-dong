package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class PhongBan {
    @Id
    private String id;
    private String ten;
    @OneToMany(mappedBy = "phongBan")
    @JsonIgnore
    List<GiaoVien> giaoViens;
}
