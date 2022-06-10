package com.example.websitemanageschooltinhdong.dto.response;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import lombok.Data;

import java.util.List;

@Data
public class HocSinhLopDTOReponse {
    private String idgv;
    private List<HocSinh> hocSinhs;
}
