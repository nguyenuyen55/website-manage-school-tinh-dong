package com.example.websitemanageschooltinhdong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class HocSinhReponse {
    public HocSinhReponse() {
    }

    public HocSinhReponse(String idhs) {
        this.idhs = idhs;
    }

    private String idhs;

}
