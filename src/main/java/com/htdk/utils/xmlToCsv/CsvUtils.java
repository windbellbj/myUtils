package com.htdk.utils.xmlToCsv;

import com.alibaba.fastjson.JSONException;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;

public class CsvUtils {



    public static File start(String fileName,String data) throws IOException {
        FileUtils.writeStringToFile(new File("file/csv/"+fileName.replace(".xml",".csv")), Json2Csv(data),"GBK");
        return new File("file/csv/"+fileName.replace(".xml",".csv"));
    }


    public static String Json2Csv(String json) throws JSONException {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(json);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        String csv = null;
        try {
            csv = CDL.toString(jsonArray);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
        }
        return csv;
    }


}
