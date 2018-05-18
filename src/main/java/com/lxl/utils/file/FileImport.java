//package com.lxl.utils.file;
//
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//
///**
// * @auther lixinlong
// * @create 2018/5/18
// */
//public class FileImport {
//
//    Object logger = null;
//    @RequestMapping(value = "/fileupload",method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
//    public String uploadFile(HttpServletRequest request, @RequestParam(value = "file",required = false) MultipartFile file) {
//        String url = "http://192.168.31.147:8089";
//        String fullPath = request.getRealPath("/image/upload/");
//        String saveFilePath = null;
//        CloseableHttpClient hc = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);
//        if (file != null) {
//            //如果文件夹不存在则创建
//            try {
//                if (!new File(fullPath).exists()) {
//                    new File(fullPath).mkdirs();
//                }
//            } catch (Exception e) {
//                return "Exception: e";
//            }
//            MultipartFile uploadFile = file;
//            if (!uploadFile.isEmpty())
//                try {
//                    // 获取图片的文件名
//                    String fileName = uploadFile.getOriginalFilename();
//                    // 获取图片的扩展名
//                    String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
//                    //新的文件名
//                    String newFileName = System.currentTimeMillis() + "." + extensionName;
//                    newFileName = newFileName.replaceAll(",", "&");
//                    saveFilePath = fullPath + newFileName;
//                    //转存文件
//                    File file1 = new File(saveFilePath);
//                    uploadFile.transferTo(file1);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    return "Exception: e";
//                }
//        }
//        else {
//            return "上传文件不能为空"+ "uploadedFile";
//        }
//        int i = saveFilePath.indexOf("image");
//        String str = saveFilePath.substring(i - 1);
//        String s = str.replaceAll("\\\\", "/");
//        return s;
//    }
//}
