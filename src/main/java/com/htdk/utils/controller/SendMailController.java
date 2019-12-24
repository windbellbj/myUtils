package com.htdk.utils.controller;

import com.alibaba.fastjson.JSONObject;
import com.htdk.utils.ftp.FTPUtil;
import com.htdk.utils.mail.EmailUtilsTwo;
import com.htdk.utils.xmlToCsv.CsvUtils;
import com.htdk.utils.xmlToCsv.XmlToString;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.htdk.utils.controller.PiUtilsController.forceDelete;
import static com.htdk.utils.xmlToCsv.XmlHelper.Dom2Map;

@RestController
@RequestMapping("/")
public class SendMailController {


    private static final Logger LOGGER = LoggerFactory.getLogger(PiUtilsController.class);

    @Value("${two.file.address}")
    public String fileAddress;
    @Value("${two.mail.receiver}")
    public String receiver;

    String content = "";
    @RequestMapping("send")
    public void send() throws DocumentException, IOException {
//        String pathDir = System.getProperty("user.dir");
        String pathDir = "";
        FTPUtil ftpUtil = new FTPUtil();
        File dirFile = new File(pathDir+"/file2");
        if(!dirFile.exists()){ dirFile.mkdirs();}
        boolean flag = ftpUtil.downloadFiles(fileAddress, pathDir+"/file2");
        if(flag){

            if(dirFile.listFiles().length>0){
                File file = dirFile.listFiles()[0];
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(new ByteArrayInputStream(XmlToString.start(file).getBytes()));
                Element incomingForm = document.getRootElement();
                List<Object> item = Arrays.asList(Dom2Map(incomingForm).get("Item"));
//                List<Item> item = JSONObject.parseArray(JSONObject.toJSONString(object),Item.class);

                if(item!=null && item.size()>0){
                    item = changeField(item);
                    File start = CsvUtils.start(file.getName(), JSONObject.toJSONString(item));
                    if(StringUtils.isNotEmpty(receiver)){
                        List<String> pops = Arrays.asList(StringUtils.split(receiver,","));
                        new EmailUtilsTwo().sendEmail(start,pops);
                        //删除文件
                        ftpUtil.deleteFiles(fileAddress+file.getName());
                        LOGGER.info("删除服务器文件：----------------"+fileAddress+file.getName());
                        LOGGER.info("邮件发送成功,处理完成");
                    }else {
                        LOGGER.error("没有收件人");
                    }
                    forceDelete(start);
                    forceDelete(file);
                }
            }else{
                LOGGER.info("没有查询到文件");
            }
        }else{
            LOGGER.info("没有查询到文件");
        }
    }

    /**
     * 将一个对象里所有的空值属性设置成null
     * @param objects
     * @return
     */
    public List<Object> changeField(List<Object> objects){
        List<Object> arrayList = new ArrayList<>();
        for (Object o : objects) {
            Class c=o.getClass();
            try {
                HashMap<String,String> hashMap = (HashMap<String,String>) o;
                for (Map.Entry<String, String> obj : hashMap.entrySet()) {
                    obj.setValue("\t"+obj.getValue());
                }
                arrayList.add(hashMap);
            }  catch (SecurityException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

}
