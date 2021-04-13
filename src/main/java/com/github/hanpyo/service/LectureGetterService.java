package com.github.hanpyo.service;

import com.github.hanpyo.entity.Lecture;
import com.github.hanpyo.entity.LectureTime;
import com.github.hanpyo.repository.LectureRepository;
import com.github.hanpyo.repository.LectureTimeRepository;
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

    private final RestTemplate restTemplate;
    private final LectureRepository lectureRepository;
    private final LectureService lectureService;
    private final LectureTimeRepository lectureTimeRepository;

    private static final String url = "https://kut90.koreatech.ac.kr/nexacroController.do";
    private static final String xmlString = "<Root xmlns=\"http://www.nexacroplatform.com/platform/dataset\"><Parameters><Parameter id=\"method\">getList_sp</Parameter><Parameter id=\"sqlid\">NK_SOT_MST.NP_SELECT_12</Parameter><Parameter id=\"programid\">SO0290S01</Parameter><Parameter id=\"locale\" /></Parameters><Dataset id=\"input1\"><ColumnInfo><Column id=\"YR\" type=\"string\" size=\"4000\" /><Column id=\"TERM_DIV\" type=\"string\" size=\"4000\" /><Column id=\"GRAD_DIV\" type=\"string\" size=\"4000\" /><Column id=\"DEPT_CD\" type=\"string\" size=\"4000\" /><Column id=\"CORS_DIV\" type=\"string\" size=\"4000\" /><Column id=\"SYN_YN\" type=\"string\" size=\"4000\" /><Column id=\"CORS_NM\" type=\"string\" size=\"4000\" /><Column id=\"USER_ID\" type=\"string\" size=\"4000\" /><Column id=\"DATA_DIV\" type=\"string\" size=\"4000\" /></ColumnInfo><Rows><Row><Col id=\"YR\">2021</Col><Col id=\"TERM_DIV\">10</Col><Col id=\"GRAD_DIV\">10</Col><Col id=\"DEPT_CD\" /><Col id=\"CORS_DIV\" /><Col id=\"SYN_YN\" /><Col id=\"CORS_NM\" /><Col id=\"DATA_DIV\">P</Col></Row></Rows></Dataset></Root>";

    @Autowired
    public LectureGetterService(RestTemplate restTemplate, LectureRepository lectureRepository, LectureService lectureService, LectureTimeRepository lectureTimeRepository) {
        this.restTemplate = restTemplate;
        this.lectureRepository = lectureRepository;
        this.lectureService = lectureService;
        this.lectureTimeRepository = lectureTimeRepository;
    }

    private ResponseEntity<String> callApiEndpoint() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);

        HttpEntity<String> request = new HttpEntity<>(xmlString, headers);

        return restTemplate.postForEntity(url, request, String.class);
    }

    public List<Lecture> getLectureList() throws Exception {
        List<Lecture> result = new ArrayList<>();

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

            result.add(Lecture.from(map));

            List<LectureTime> lectureTimes = new ArrayList<>();

            if (map.get("LECT_TM") != null) {
                String prevDay = "";
                for (String time : ((String) map.get("LECT_TM")).split(",")) {
                    if (time.length() != 8) time = prevDay + time;
                    lectureTimes.add(LectureTime.from(time, result.get(i)));
                    prevDay = time.substring(0, 1);
                }
            }

            result.get(i).setLectureTimes(lectureTimes);
        }

        return result;
    }

    @Transactional
    public void updateLectures(List<Lecture> lectures) {
        List<Lecture> addLectureList = new ArrayList<>();
        List<Lecture> updateLectureList = new ArrayList<>();

        List<Lecture> lectureList = new ArrayList<>(lectures);
        List<Lecture> lectureDBList = lectureService.getLectures();

        for (Lecture lecture : lectureList) {
            boolean bDBExistLecture = false;

            for (Lecture targetLecture: lectureDBList) {
                if (lecture.getId().equals(targetLecture.getId())) {
                    bDBExistLecture = true;
                    if (!lecture.equals(targetLecture)) updateLectureList.add(lecture);
                    lectureDBList.remove(targetLecture);
                    break;
                }
            }

            if (!bDBExistLecture) addLectureList.add(lecture);
        }

        lectureRepository.deleteAll(lectureDBList);
        lectureRepository.saveAll(addLectureList);
        lectureRepository.saveAll(updateLectureList);

        for (Lecture lecture : addLectureList) lectureTimeRepository.saveAll(lecture.getLectureTimes());
        for (Lecture lecture : updateLectureList) {
//            LectureTime 업데이트 로직 필요
            lectureTimeRepository.saveAll(lecture.getLectureTimes());
        }
    }
}
