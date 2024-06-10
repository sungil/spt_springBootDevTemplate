package com.sptek.webfw.config.springSecurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //spring security 의 AuthenticaionFilter 에서 UsernamePasswordAuthenticationToken를 생성해줌
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        //email 값을 Name(일반적id개념) 으로 사용하는 케이스
        String email = token.getName();
        String password = (String) token.getCredentials();
        log.debug("user info : {} , {}", email, password);

        // DB 에서 사용자 정보가 실제로 유효한지 확인 후 인증된 Authentication 리턴
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(email);
        if (bCryptPasswordEncoder.matches(password, customUserDetails.getPassword()) == false) {
            throw new BadCredentialsException(customUserDetails.getUsername() + " : Password does not match.");
        }
        //todo : principal 로 customUserDetails 전체를 주는게 맞을까? name만 주는게 맞을까?
        //return new UsernamePasswordAuthenticationToken(customUserDetails.getUsername(), password, customUserDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(customUserDetails, password, customUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}