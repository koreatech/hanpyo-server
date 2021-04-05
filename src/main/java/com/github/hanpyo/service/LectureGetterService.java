package com.github.hanpyo.service;

import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.repository.LectureRepository;
import com.github.hanpyo.util.XMLParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LectureGetterService {

    private RestTemplate restTemplate;
    private LectureRepository lectureRepository;

    private static String url = "https://kut90.koreatech.ac.kr/nexacroController.do";
    private static String xmlString = "<Root xmlns=\"http://www.nexacroplatform.com/platform/dataset\"><Parameters><Parameter id=\"method\">getList_sp</Parameter><Parameter id=\"sqlid\">NK_SOT_MST.NP_SELECT_12</Parameter><Parameter id=\"programid\">SO0290S01</Parameter><Parameter id=\"locale\" /></Parameters><Dataset id=\"input1\"><ColumnInfo><Column id=\"YR\" type=\"string\" size=\"4000\" /><Column id=\"TERM_DIV\" type=\"string\" size=\"4000\" /><Column id=\"GRAD_DIV\" type=\"string\" size=\"4000\" /><Column id=\"DEPT_CD\" type=\"string\" size=\"4000\" /><Column id=\"CORS_DIV\" type=\"string\" size=\"4000\" /><Column id=\"SYN_YN\" type=\"string\" size=\"4000\" /><Column id=\"CORS_NM\" type=\"string\" size=\"4000\" /><Column id=\"USER_ID\" type=\"string\" size=\"4000\" /><Column id=\"DATA_DIV\" type=\"string\" size=\"4000\" /></ColumnInfo><Rows><Row><Col id=\"YR\">2021</Col><Col id=\"TERM_DIV\">10</Col><Col id=\"GRAD_DIV\">10</Col><Col id=\"DEPT_CD\" /><Col id=\"CORS_DIV\" /><Col id=\"SYN_YN\" /><Col id=\"CORS_NM\" /><Col id=\"DATA_DIV\">P</Col></Row></Rows></Dataset></Root>";

    @Autowired
    public LectureGetterService(RestTemplate restTemplate, LectureRepository lectureRepository, LectureRepository lectureRepository1) {
        this.restTemplate = restTemplate;
        this.lectureRepository = lectureRepository1;
    }

    private ResponseEntity<String> callApiEndpoint() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);

        HttpEntity<String> request = new HttpEntity<>(xmlString, headers);

        return restTemplate.postForEntity(url, request, String.class);
    }

    public List<Map<String, Object>> getLectureList() throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();

        final ResponseEntity<String> response = callApiEndpoint();

        Document document = XMLParser.parseXmlString(response.getBody());

        NodeList RowsTagList = document.getElementsByTagName("Row");

        for (int i = 0; i < RowsTagList.getLength(); i++) {
            Map<String, Object> map = new HashMap<>();
            Element row = (Element) RowsTagList.item(i);
            NodeList ColsTagList = row.getElementsByTagName("Col");

            for (int j = 0; j < ColsTagList.getLength(); j++) {
                Element item = (Element) ColsTagList.item(j);
                String id = item.getAttributes().getNamedItem("id").getNodeValue();

                map.put(id, item.getTextContent());
            }
            result.add(map);
        }

        return result;
    }

    @Transactional
    public void createLectures(List<Map<String, Object>> lectures) {
        for (int i = 0; i < lectures.size(); i++) {
            Lecture lecture = Lecture.builder()
                    .lectureInfo(lectures.get(i))
                    .build();

            lectureRepository.save(lecture);
        }
    }
}
