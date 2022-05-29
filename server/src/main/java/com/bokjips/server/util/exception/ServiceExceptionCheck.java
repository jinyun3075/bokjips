package com.bokjips.server.util.exception;


import com.bokjips.server.domain.user.dto.UserRequestDto;

import java.util.regex.Pattern;

public class ServiceExceptionCheck {
   public void checkInsertUser(UserRequestDto dto) throws Exception{
      if(dto.getEmail().equals("")||dto.getName().equals("")||dto.getPassword().equals("")){
         throw new Exception("모든 사항을 입력해주세요.");
      }
      if (!(dto.getPassword().length() > 5 & dto.getPassword().length() < 15)) {
         throw new Exception("비밀번호는 6자 이상 15자 이하로 입력해주세요.");
      }

      String passwordPattern = "[a-z0-9ㄱ-ㅎ가-힣\\\\!\\\\@\\\\#]*";
      if (!Pattern.matches(passwordPattern, dto.getPassword())) {
         throw new Exception("영문 및 숫자 @,!,# 로만 입력해주세요");
      }

      String emailPattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]+$";
      if (!Pattern.matches(emailPattern, dto.getEmail())) {
         throw new Exception("이메일 형식이 아닙니다.");
      }
   }
}
