package com.example.websitemanageschooltinhdong.security.service;

import com.example.websitemanageschooltinhdong.domain.NguoiDung;
import com.example.websitemanageschooltinhdong.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(s);
      if(nguoiDung==null){
          throw new UsernameNotFoundException("Could not find user");
      }

        return new CustomUserDetails(nguoiDung);
    }
}
