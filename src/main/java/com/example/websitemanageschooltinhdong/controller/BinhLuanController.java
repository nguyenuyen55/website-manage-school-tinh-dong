package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.BinhLuan;
import com.example.websitemanageschooltinhdong.domain.TraLoiBinhLuan;
import com.example.websitemanageschooltinhdong.dto.request.ReplyCommentDTO;
import com.example.websitemanageschooltinhdong.exception.RecordNotFoundException;
import com.example.websitemanageschooltinhdong.repository.BinhLuanRepository;
import com.example.websitemanageschooltinhdong.repository.TraloiBinhLuanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/binhluan")
public class BinhLuanController {
    @Autowired
    BinhLuanRepository binhLuanRepository;
    @Autowired
    TraloiBinhLuanRepository traloiBinhLuanRepository;

    @GetMapping("/list/{idchuong}")
    public ResponseEntity<List<BinhLuan>> getAll(@PathVariable("idchuong") int id) {
        if (binhLuanRepository.findAllByIdBaiGiang(id).size()==0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(binhLuanRepository.findAllByIdBaiGiang(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BinhLuan> create(@RequestBody BinhLuan binhLuan) {
        binhLuan.setTime(java.time.LocalDate.now());
        return new ResponseEntity<>(binhLuanRepository.save(binhLuan), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id) {
        Optional<BinhLuan> binhLuan = binhLuanRepository.findById(id);
        if(binhLuan.isEmpty()){
            throw new RecordNotFoundException("Không tìm thấy bình luận chính của đối tượng trả lời bình luận này ");
        }
        binhLuanRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/reply/create")
    public ResponseEntity<TraLoiBinhLuan> createRelyComment(@RequestBody ReplyCommentDTO replyBinhLuan) {
        Optional<BinhLuan> binhLuan = binhLuanRepository.findById(replyBinhLuan.getIdBinhLuan());
        if(binhLuan.isEmpty()){
            throw new RecordNotFoundException("Không tìm thấy bình luận chính của đối tượng trả lời bình luận này ");
        }
        replyBinhLuan.setThoigian(java.time.LocalDate.now());
        TraLoiBinhLuan traLoiBinhLuan = new TraLoiBinhLuan();
        traLoiBinhLuan.setNoiDung(replyBinhLuan.getNoiDung());
        traLoiBinhLuan.setBinhLuan(binhLuan.get());
        traLoiBinhLuan.setThoigian(replyBinhLuan.getThoigian());
        return new ResponseEntity<>(traloiBinhLuanRepository.save(traLoiBinhLuan), HttpStatus.OK);
    }
    @DeleteMapping("/delete/reply/{id}")
    public ResponseEntity<Boolean> deleteReply(@PathVariable("id") int id) {

        Optional<TraLoiBinhLuan> traLoiBinhLuan = traloiBinhLuanRepository.findById(id);
        if(traLoiBinhLuan.isEmpty()){
            throw new RecordNotFoundException("Không tìm thấy bình luận chính của đối tượng trả lời bình luận này ");
        }
        traLoiBinhLuan.get().setBinhLuan(null);
        traloiBinhLuanRepository.save(traLoiBinhLuan.get());
        traloiBinhLuanRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @GetMapping("/list/reply/{idbinhluan}")
    public ResponseEntity<List<TraLoiBinhLuan>> getAllTraLoiBinhLuan(@PathVariable("idbinhluan") int id) {
        if (traloiBinhLuanRepository.findAllByBinhLuan_Id(id).size()==0) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(traloiBinhLuanRepository.findAllByBinhLuan_Id(id), HttpStatus.OK);
    }

}
