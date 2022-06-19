package com.example.websitemanageschooltinhdong.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
public class TinTucDTO {
    private int id;
    private String tieuDe;
    private String noiDung;
    private String hinhAnh;
    private List<ImageDTO> imageList;
}
