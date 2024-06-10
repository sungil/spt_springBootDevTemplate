package com.sptek.webfw.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Profile(value = { "!prd" }) //prd가 아닐때
@Profile(value = { "local"})
@Configuration
@Slf4j
public class DataSourceConfigForDevWithH2 {
    //H2를 사용하여 datasource Bean을 만드는 경우 property 설정으로 처리됨 (필요한게 있다면 추가하기 위해 만듬)
}
