package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.dto.request.RequestRestPassword;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.NguoiDungRepository;
import com.example.websitemanageschooltinhdong.service.impl.EmailSenderService;
import com.example.websitemanageschooltinhdong.service.impl.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
public class NguoiDungController {
    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    OtpService otpService;

    @GetMapping("/api/nguoiDung/{ten}")
    public ResponseEntity<NguoiDung> getNguoiDung(@PathVariable("ten") String ten) {
        return new ResponseEntity<NguoiDung>(nguoiDungRepository.findById(9).get(), HttpStatus.OK);
    }

    //quen mat khau
//    @PostMapping("send-otp-forgot-password")
//    public ResponseEntity<Boolean> sendCodeResetPassword(@RequestBody RequestRestPassword restPassword) {
//        NguoiDung user = nguoiDungRepository.findByTenDangNhap(restPassword.getUsername());
//        if (user == null) {
//            throw new RecordNotFoundException("not found user");
//        }
//        AtomicBoolean isCheck = new AtomicBoolean(false);
//        nguoiDungRepository.findByTenDangNhapOptinal(restPassword.getUsername())
//                .ifPresent((value) -> {
//                            String otp = otpService.generateOTP(restPassword.getUsername());
//                            //gamil vào
//                            boolean isOtp = emailSenderService.sendEmail(value.getEmail(), "this is subject", otp);
//                            if (isOtp) {
//                                isCheck.set(true);
//                            } else {
//                                isCheck.set(false);
//                            }
//                        }
//                );
//        if (isCheck.get()) {
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
//        }
//
//    }
}
