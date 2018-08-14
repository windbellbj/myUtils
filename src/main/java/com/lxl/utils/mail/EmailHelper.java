package com.lxl.utils.mail;

/**
 * <dl>
 * <dt>EmailHelper</dt>
 * <dd>@auther lixinlong</dd>
 * <dd>@create 2018/8/3</dd>
 * <dd></dd>
 * </dl>
 */
import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class EmailHelper {

    private String host;
    private String username;
    private String password;
    private String from;

    private String[] to;
    private String subject;
    private String htmlContent;
    private List<String> attachedFileNames;

    public EmailHelper(String host, String username, String password, String from) throws AddressException, MessagingException{
        this.host = host;
        this.username = username;
        this.password = password;
        this.from = from;
    }

    public void send() throws Exception{

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);

        final String username1 = username;
        final String password1 = password;

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username1, password1);
                    }
                });

        Message message = new MimeMessage(session);


        message.setFrom(new InternetAddress(from));

        InternetAddress[] receptientsEmail=new InternetAddress[to.length];
        for(int i=0;i<to.length;i++){
            receptientsEmail[i]=new InternetAddress(to[i]);
        }

        //多个收件人
        message.setRecipients(Message.RecipientType.TO,receptientsEmail);

        message.setSubject(subject);

        Multipart multipart = new MimeMultipart();

        if (htmlContent != null){
            System.out.println(" has html ");

            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html");
            multipart.addBodyPart(htmlPart);
        }
        MimeBodyPart mimeBodyPart;
        //多个附件
        if(attachedFileNames!=null && attachedFileNames.size()>0) {
            for (String attachedFileName : attachedFileNames) {
                File file = new File(attachedFileName);
                if (file.exists()) {
                    String fileSource = attachedFileName;
                    String fileName = attachedFileName;
                    mimeBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(fileSource);
                    mimeBodyPart.setDataHandler(new DataHandler(source));
                    mimeBodyPart.setFileName(MimeUtility.encodeText(fileName));
                    multipart.addBodyPart(mimeBodyPart);// Put parts in
                }
            }
        }

        message.setContent(multipart);
        Transport.send(message);

        System.out.println(" Sent ");
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public void setAttachedFileName(List<String> attachedFileNames) {
        this.attachedFileNames = attachedFileNames;
    }
}