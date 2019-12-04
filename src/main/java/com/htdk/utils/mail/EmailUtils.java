package com.htdk.utils.mail;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.io.File;
import java.util.List;

public class EmailUtils{
    public void sendEmail(File file, List<String> pops){
        MultiPartEmail mail = new MultiPartEmail();
        mail.setHostName("smtp.partner.outlook.cn");
        mail.setAuthentication("Note.IT@HTDKgroup.com", "Password9");
        mail.setCharset("UTF-8");
        try {
            mail.setFrom("Note.IT@HTDKgroup.com", "Note.IT@HTDKgroup.com");
            if (pops.size()>0){
                for (String pop : pops) {
                    mail.addTo(pop, "");
                }
            }
//            mail.addTo("jasper.x.jin@htdkgroup.com", "");
            mail.setSubject("新增物料提醒");
            mail.setMsg("您有新增的物料信息，请查看附件");
            mail.setStartTLSEnabled(true);
            mail.setSmtpPort(587);

            //如果有附件
            if(file != null){
                //创建附件
                EmailAttachment emailAttachment = new EmailAttachment();
                //封装附件的位置
                emailAttachment.setPath(file.getPath());
                //封装附件的标题
                emailAttachment.setName(file.getName());
                //将附件加入到上述的邮件中
                mail.attach(emailAttachment);
            }

            mail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}