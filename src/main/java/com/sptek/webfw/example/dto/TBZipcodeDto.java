package com.sptek.webfw.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TBZipcodeDto {
    private String zipNo;
    private String sido;
    private String sidoEng;
    private String sigungu;
    private String sigunguEng;
    private String eupmyun;
    private String eupmyunEng;
    private String doroCd;
    private String doro;
    private String doroEng;
    private String undergroundYn;
    private int buildNo1;
    private int buildNo2;
    private String buildNoManageNo;
    private String daryangNm;
    private String buildNm;
    private String dongCd;
    private String dongNm;
    private String ri;
    private String hDongNm;
    private String sanYn;
    private int zibun1;
    private String eupmyunDongSn;
    private int zibun2;
    private String zipNoOld;
    private String zipSn;
}
