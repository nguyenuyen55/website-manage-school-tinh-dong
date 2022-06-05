package com.example.websitemanageschooltinhdong.dto.request;

import com.example.websitemanageschooltinhdong.domain.ChuDe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CauHoiDTO {
    @NotBlank(message = "nội dung không được để trống.")
    private String noiDung;
    @NotBlank(message = "họ và tên không được để trống.")
    private String hoTen;
    @Email
    @NotBlank(message = "email không được để trống.")
    private String email;
    @Length(min = 9,message = "Không được ít hơn 9 kí tự.")
    @NotBlank(message = "số điện thoại không được để trống.")
    private String phone;
    @NotBlank(message = "tiêu đề không được để trống.")
    private String tieuDe;
    private int idChuDe;
}
