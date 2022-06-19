package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.dto.request.PassworDTO;
import com.example.websitemanageschooltinhdong.dto.request.RequestRestPassword;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.NguoiDungRepository;
import com.example.websitemanageschooltinhdong.service.impl.EmailSenderService;
import com.example.websitemanageschooltinhdong.service.impl.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class NguoiDungController {
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    PasswordEncoder passwordEnco;

    @PutMapping("/api/change-password")
    public ResponseEntity<NguoiDung> getNguoiDung(@RequestBody PassworDTO passworDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(passworDTO.getUsername());

        if (!passwordEncoder.matches(passworDTO.getPasswordold(), nguoiDung.getMatKhau())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            nguoiDung.setMatKhau(passwordEncoder.encode(passworDTO.getPasswornew()));
            nguoiDungRepository.save(nguoiDung);
        }
        return new ResponseEntity<NguoiDung>(nguoiDungRepository.save(nguoiDung), HttpStatus.OK);
    }


}
