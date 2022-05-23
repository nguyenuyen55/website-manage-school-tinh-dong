package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
public class ChuDe {
    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "chuDe")
    @JsonIgnore
    List<CauHoi> cauHois;

}
