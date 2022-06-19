package com.example.websitemanageschooltinhdong.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class HocSinhSearchDTO {
    private String ten;
    private String ngaysinh;
    private String diaChi;
    private String tenlop;
    private int khoa;
}
