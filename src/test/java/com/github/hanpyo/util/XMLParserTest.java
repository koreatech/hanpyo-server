package com.github.hanpyo.util;

import static org.assertj.core.api.BDDAssertions.*;

import com.github.hanpyo.test.MockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class XMLParserTest extends MockTest {

    @Test
    @DisplayName("XML String을 Document로 변환가능해야한다.")
    public void parseXmlStringTest() throws Exception {
        // given
        String src = "<test><test2>test</test2></test>";

        // when
        Document document = XMLParser.parseXmlString(src);
        NodeList TestTagList = document.getElementsByTagName("test");
        Element element = (Element) TestTagList.item(0);

        // then
        then(element.getTextContent()).isEqualTo("test");
    }
}
