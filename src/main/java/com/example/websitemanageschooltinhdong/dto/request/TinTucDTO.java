package com.example.websitemanageschooltinhdong.dto.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class TinTucDTO {
    private String tieuDe;
    private String noiDung;
    private String hinhAnh;
    private List<ImageDTO> imageList;
}
