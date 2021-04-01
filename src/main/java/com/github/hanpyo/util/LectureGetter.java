package com.github.hanpyo.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class LectureGetter {

    private static String url = "https://kut90.koreatech.ac.kr/nexacroController.do";
    private static String xmlString = "<Root xmlns=\"http://www.nexacroplatform.com/platform/dataset\"><Parameters><Parameter id=\"method\">getList_sp</Parameter><Parameter id=\"sqlid\">NK_SOT_MST.NP_SELECT_12</Parameter><Parameter id=\"programid\">SO0290S01</Parameter><Parameter id=\"locale\" /></Parameters><Dataset id=\"input1\"><ColumnInfo><Column id=\"YR\" type=\"string\" size=\"4000\" /><Column id=\"TERM_DIV\" type=\"string\" size=\"4000\" /><Column id=\"GRAD_DIV\" type=\"string\" size=\"4000\" /><Column id=\"DEPT_CD\" type=\"string\" size=\"4000\" /><Column id=\"CORS_DIV\" type=\"string\" size=\"4000\" /><Column id=\"SYN_YN\" type=\"string\" size=\"4000\" /><Column id=\"CORS_NM\" type=\"string\" size=\"4000\" /><Column id=\"USER_ID\" type=\"string\" size=\"4000\" /><Column id=\"DATA_DIV\" type=\"string\" size=\"4000\" /></ColumnInfo><Rows><Row><Col id=\"YR\">2021</Col><Col id=\"TERM_DIV\">10</Col><Col id=\"GRAD_DIV\">10</Col><Col id=\"DEPT_CD\" /><Col id=\"CORS_DIV\" /><Col id=\"SYN_YN\" /><Col id=\"CORS_NM\" /><Col id=\"DATA_DIV\">P</Col></Row></Rows></Dataset></Root>";

    public static RestTemplate restTpl = new RestTemplate();

    public static String getLectureData() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);

        HttpEntity<String> request = new HttpEntity<>(xmlString, headers);

        final ResponseEntity<String> response = restTpl.postForEntity(url, request, String.class);

        return response.getBody();
    }
}
