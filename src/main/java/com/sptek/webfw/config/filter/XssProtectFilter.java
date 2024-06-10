package com.sptek.webfw.config.filter;



/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sptek.webfw.support.HttpServletRequestWrapperSupport;
import com.sptek.webfw.util.SecureUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Order(1)
//@WebFilter적용시 @Component 사용하지 않아야함(@Component 적용시 모든 요청에 적용됨)
//@Component
@WebFilter(urlPatterns = "/*") //ant 표현식 사용 불가 ex: /**
public class XssProtectFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("[Filter >>> ]");
        HttpServletRequestWrapperSupport wrappedRequest = new HttpServletRequestWrapperSupport(request);
        String reqBody = IOUtils.toString(wrappedRequest.getReader()); //컨트럴러 이전 단계에서 Request 스트림이 읽어졌기 때문에 대체 request를 생성해서 넘겨줘야 함

        if (!StringUtils.isEmpty(reqBody)) {
            Map<String, Object> orgJsonObject = new ObjectMapper().readValue(reqBody, HashMap.class);
            Map<String, Object> newJsonObject = new HashMap<>();
            orgJsonObject.forEach((key, value) -> newJsonObject.put(key, SecureUtil.charEscape(value.toString())));
            
            //대체 request를 생성해서 넘김
            wrappedRequest.resetInputStream(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(newJsonObject).getBytes());
        }

        filterChain.doFilter(wrappedRequest, response);
    }
}


 */
