package com.bokjips.server.util.security.service;

import com.bokjips.server.domain.user.entity.BokjipsUser;
import com.bokjips.server.domain.user.repository.BokjipsUserRepository;
import com.bokjips.server.util.security.dto.BokjipsUserAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BokjipsUserDetailsService implements UserDetailsService {

    private final BokjipsUserRepository bokjipsUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailService LoadUserByUserName--------------"+username);

        BokjipsUser entity = bokjipsUserRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(""));

        log.info(entity);

        BokjipsUserAuthDto bokjipsUserAuthDto = new BokjipsUserAuthDto(
                entity.getEmail(),
                entity.getPassword(),
                entity.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );

        bokjipsUserAuthDto.setEmail(entity.getEmail());
        bokjipsUserAuthDto.setName(entity.getName());

        return bokjipsUserAuthDto;
    }

}
