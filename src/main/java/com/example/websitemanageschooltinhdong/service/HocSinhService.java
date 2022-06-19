package com.example.websitemanageschooltinhdong.service;

import com.example.websitemanageschooltinhdong.domain.HocSinh;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhDTO;
import com.example.websitemanageschooltinhdong.dto.request.HocSinhSearchDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HocSinhService {


    List<HocSinh> findALL();
    List<HocSinhSearchDTO> findALLSearch(String name);

    //find all hocSinh
    List<HocSinh> findAllIdLop(int id);

    HocSinh create(HocSinhDTO hocSinhDTO);

    HocSinh update(HocSinhDTO hocSinhDTO);

    HocSinh detailById(String id);

    Boolean delete(String id);

}
