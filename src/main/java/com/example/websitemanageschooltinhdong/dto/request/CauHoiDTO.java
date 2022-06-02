package com.example.websitemanageschooltinhdong.dto.request;

import com.example.websitemanageschooltinhdong.domain.ChuDe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CauHoiDTO {
    private String noiDung;

    private String hoTen;

    private String email;
    private String phone;

    private String tieuDe;

    private int idChuDe;
}
