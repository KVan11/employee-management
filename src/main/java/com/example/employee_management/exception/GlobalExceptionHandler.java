package com.example.employee_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý ResponseStatusException 
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", ex.getStatusCode().value());
        error.put("message", ex.getReason());

        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    //Xử lý lỗi khi dữ liệu trùng lặp (ví dụ: email đã tồn tại)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.CONFLICT.value());
        error.put("message", "Email đã tồn tại hoặc vi phạm ràng buộc");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    //Xử lý lỗi định dạng request body không hợp lệ
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("message", "Định dạng request không hợp lệ");
        
        String errorDetail = "Request body bị lỗi hoặc có kiểu dữ liệu không đúng";
        if (ex.getCause() != null) {
            errorDetail = ex.getCause().getMessage();
        }
        error.put("details", errorDetail);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //Xử lý các exception khác không được định nghĩa
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("message", "Lỗi server nội bộ");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
