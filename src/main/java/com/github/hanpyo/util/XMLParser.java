package com.github.hanpyo.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public abstract class XMLParser {
    public static Document parseXmlString(String src) throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(src);

        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document document = builder.parse(input);
        document.getDocumentElement().normalize();
        return document;
    }

    public static void getData(Document document) {
        NodeList RowsTagList = document.getElementsByTagName("Row");

        for (int i = 0; i < RowsTagList.getLength(); i++) {
            Element row = (Element) RowsTagList.item(i);
            NodeList ColsTagList = row.getElementsByTagName("Col");

            for (int j = 0; j < ColsTagList.getLength(); j++) {
                Element item = (Element) ColsTagList.item(j);
                String id = item.getAttributes().getNamedItem("id").getNodeValue();

                System.out.println(id + " " + item.getTextContent());
            }
        }
    }
}
