package com.example.websitemanageschooltinhdong.controller;

import com.example.websitemanageschooltinhdong.domain.CauHoi;
import com.example.websitemanageschooltinhdong.domain.ChuDe;
import com.example.websitemanageschooltinhdong.dto.request.CauHoiDTO;
import com.example.websitemanageschooltinhdong.exception.ErrorResponse;
import com.example.websitemanageschooltinhdong.repository.ChuDeRepository;
import com.example.websitemanageschooltinhdong.service.CauHoiService;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
@Autowired
    CauHoiService cauHoiService;
    @Autowired
    ChuDeRepository chuDeRepository;
    @PostMapping("create")
    public ResponseEntity<CauHoi> createCauHoi(@Valid @RequestBody CauHoiDTO cauHoi) {
        return new ResponseEntity<>(cauHoiService.createCauHoi(cauHoi), HttpStatus.OK);
    }

@GetMapping("/chude")
    public ResponseEntity<List<ChuDe>> listResponseEntity(){
    return new ResponseEntity<>(chuDeRepository.findAll(), HttpStatus.OK);

}
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse("error validation", false, errors);
        return errorResponse;
    }

}
