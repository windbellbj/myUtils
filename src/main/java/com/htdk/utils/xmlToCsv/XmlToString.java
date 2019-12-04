package com.htdk.utils.xmlToCsv;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class XmlToString{

    public static String start(File file)  {
        SAXReader reader = new SAXReader();

        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        String documentStr = document.asXML();

        return documentStr;
    }


}