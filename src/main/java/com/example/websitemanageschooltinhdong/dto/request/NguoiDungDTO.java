package com.example.websitemanageschooltinhdong.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO {
    private int id;
    private String tenDangNhap;
    private String matKhau;
    private String quyen;
}
