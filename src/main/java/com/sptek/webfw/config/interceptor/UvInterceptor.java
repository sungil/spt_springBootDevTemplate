package com.sptek.webfw.config.interceptor;

import com.sptek.webfw.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

/*
UV 관련 처리를 위한 인터셉터
 */
@Component
@Slf4j
public class UvInterceptor implements HandlerInterceptor {
    private final String uvCheckCookieName = "uvCheck";
    private final String uvCheckCookieValue = "checked";
    private final int uvCheckCookieMaxAgeSec = 60 * 60; //해당 시간동안 request 가 없었던 경우 새로운 방문으로 본다.

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("[Interceptor >>> ]");
        String checkMsg = Optional.ofNullable(CookieUtil.getCookies(uvCheckCookieName))
                .filter(cookies -> !cookies.isEmpty())
                .map(cookies -> "update expire time")
                .orElse("created new");

        log.debug("UvInterceptor : {}", checkMsg);

        ResponseCookie.ResponseCookieBuilder cookieBuilder = ResponseCookie.from(uvCheckCookieName, uvCheckCookieValue)
                .maxAge(uvCheckCookieMaxAgeSec).path("/").sameSite("Strict");
        CookieUtil.addResponseCookie(cookieBuilder.build());

        try {
            //todo: do more what you want (checkMsg 로그를 통해 uv 카운트??)

        }catch (Exception ex){
            //중요 오류가 아니기 때문에 ex를 throw 하지 않는다.
            log.error(ex.getMessage());
        }
        return true;
    }
}

