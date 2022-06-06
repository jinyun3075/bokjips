package com.bokjips.server.service.Impl;

import com.bokjips.server.domain.user.dto.UserRequestDto;
import com.bokjips.server.domain.user.dto.UserResponseDto;
import com.bokjips.server.domain.user.repository.BokjipsUserRepository;
import com.bokjips.server.service.BokjipsUserService;
import com.bokjips.server.util.exception.ServiceExceptionCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class BokjipsUserServiceImpl extends ServiceExceptionCheck implements BokjipsUserService {

    private final BokjipsUserRepository bokjipsUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserResponseDto insertUser(UserRequestDto dto) throws Exception{
        checkInsertUser(dto);
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return entityToDto(bokjipsUserRepository.save(dtoToEntity(dto)));
    }
}
