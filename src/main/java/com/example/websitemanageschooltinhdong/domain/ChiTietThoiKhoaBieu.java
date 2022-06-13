package com.example.websitemanageschooltinhdong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import java.util.List;

@Entity
@Data

@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ChiTietThoiKhoaBieu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String thu;
    private String thuTu;
    @ManyToOne
    MonHoc monHoc;
    @ManyToOne
    ThoiKhoaBieu thoiKhoaBieu;
}
