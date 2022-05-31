package com.bokjips.server.util.exception;


import com.bokjips.server.util.dto.ErrorResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Log4j2
@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler
    public ErrorResult Handle(Exception e) {
        if(e.getMessage()==null) {
            return new ErrorResult("402","null 값이 있네요.");
        }
        return new ErrorResult("500", e.getMessage());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResult Handle(SQLException  e) {
        if(e.getErrorCode()==1062){
            if(e.getMessage().split(" ")[5].equals("'bokjips_user.UK_qd50xxqf2ri1yd8vovwp2u2qj'")){
               return new ErrorResult("505", "중복 이름입니다.");
            }
            if(e.getMessage().split(" ")[5].equals("'bokjips_user.UK_anm3yov971qyjtujrgf7fvdpd'")){
                return new ErrorResult("505", "중복 이메일입니다.");
            }
            if(e.getMessage().split(" ")[5].equals("'corp.UK_oix0mbgc93djbmywtsl580rso'")){
                return new ErrorResult("505", "회사 이름 중복입니다.");
            }
        }
        if(e.getErrorCode()==1406) {
            return new ErrorResult("505","입력값이 너무 길어요");
        }
        return new ErrorResult("503", e.getMessage());
    }
}
