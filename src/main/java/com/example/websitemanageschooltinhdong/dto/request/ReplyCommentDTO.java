package com.example.websitemanageschooltinhdong.dto.request;

import com.example.websitemanageschooltinhdong.domain.BinhLuan;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class ReplyCommentDTO {
    private int id;
    private String noiDung;
    private LocalDate thoigian;
    private int idBinhLuan;
}
