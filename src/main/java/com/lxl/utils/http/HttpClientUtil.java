package com.lxl.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager cm = null;

    private static CloseableHttpClient httpClient = null;
	
	
    static {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("创建SSL连接失败");
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
//        HttpHost localhost = new HttpHost("http://waimaiopen.meituan.com", 80);
//        cm.setMaxPerRoute(new HttpRoute(localhost), 80);		//对本机80端口的socket连接上限是80
    }
    
//    private static void config(HttpRequestBase httpRequestBase) {  
////        httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
////        httpRequestBase.setHeader("Connection", "keep-alive");
////        httpRequestBase.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
////        httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//"en-US,en;q=0.5");  
////        httpRequestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");  
//          
//        // 配置请求的超时设置  
//        RequestConfig requestConfig = RequestConfig.custom()  
//                .setConnectionRequestTimeout(3000)  
//                .setConnectTimeout(3000)  
//                .setSocketTimeout(3000)  
//                .build();  
//        httpRequestBase.setConfig(requestConfig);         
//    }
    
    public static CloseableHttpClient getHttpClient() {
    	if(httpClient == null){
//    		httpClient = HttpClients.custom().setConnectionManager(cm).build();
    		
    		RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(5000)  
                    .setConnectTimeout(5000)  
                    .setSocketTimeout(5000)
//                    .setProxy(new HttpHost("127.0.0.1", 8087))
                    .build();
    		httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();
    	}
    	return httpClient;
    }

    public static InputStream getInputStream(String urlPath) throws IOException{
        InputStream inputStream=null;
        HttpURLConnection httpurlconn=null;
        try {
            URL url=new URL(urlPath);
            if(url!=null)
            {
                httpurlconn=(HttpURLConnection) url.openConnection();
                //设置连接超时时间
                httpurlconn.setConnectTimeout(3000);
                //表示使用GET方式请求
                httpurlconn.setRequestMethod("GET");
                int responsecode=httpurlconn.getResponseCode();
                if(responsecode==200)
                {
                    //从服务返回一个输入流
                    inputStream=httpurlconn.getInputStream();
                }
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inputStream;


    }


	public static String doGet(String url, Map<String, Object> params) {
		//创建参数列表
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String paramName : params.keySet()) {
        	if(params.get(paramName)!=null){
            	list.add(new BasicNameValuePair(paramName, params.get(paramName).toString()));
        	}else{
            	list.add(new BasicNameValuePair(paramName, "null"));
        	}
		}
		//url格式编码
    	String param;
		try {
			param = EntityUtils.toString(new UrlEncodedFormEntity(list, "UTF-8"));
			return HttpClientUtil.get(url, param);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
    public static String get(String url, String param) {
        // 创建默认的httpClient实例
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        CloseableHttpResponse httpResponse = null;
        // 发送get请求
        try {
        	LOGGER.info("url="+url +"?"+ param);
            // 用get方法发送http请求
            HttpGet get = new HttpGet(url +"?"+ param);
//        	config(get);
            LOGGER.info("执行get请求, uri: " + get.getURI());
            get.addHeader("Accept-Encoding", "gzip,deflate,sdch");
            
            
            httpResponse = httpClient.execute(get);
            // response实体
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity) {
                String response = EntityUtils.toString(entity);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                InputStream in = httpResponse.getEntity().getContent();
                in.close();
                LOGGER.info("响应状态码:" + statusCode);
                LOGGER.info("响应内容:" + response);
                if (statusCode == HttpStatus.SC_OK) {
                    // 成功
                    return response;
                } else {
                    return null;
                }
            }
            return null;
        } catch (IOException e) {
            LOGGER.error("httpclient请求失败", e);
            return null;
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                    httpResponse.close();
                } catch (IOException e) {
                    LOGGER.error("关闭response失败", e);
                }
            }
        }
    }
    

	public static String doPost(String url, Map<String, Object> params) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		@SuppressWarnings("rawtypes")
		Iterator iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			@SuppressWarnings("unchecked")
			Entry<String, Object> elem = (Entry<String, Object>) iterator.next();
			if(elem.getValue()!=null){
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue().toString()));
			}else{
            	list.add(new BasicNameValuePair(elem.getKey(), "null"));
        	}
		}
		
		return HttpClientUtil.post(url, list);
	}

    public static String post(String url, List<NameValuePair> list) {
        // 创建默认的httpClient实例
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        CloseableHttpResponse httpResponse = null;
        // 发送post请求
        try {
            // 用post方法发送http请求
        	HttpPost post = new HttpPost(url);
//        	config(post);
            LOGGER.info("执行post请求, uri: " + post.getURI());
            
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
            post.setEntity(uefEntity);
			
            httpResponse = httpClient.execute(post);
            // response实体
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity) {
                String response = EntityUtils.toString(entity);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                InputStream in = httpResponse.getEntity().getContent();
                in.close();
                LOGGER.info("响应状态码:" + statusCode);
                LOGGER.info("响应内容:" + response);
                if (statusCode == HttpStatus.SC_OK) {
                    // 成功
                    return response;
                } else {
                    return null;
                }
            }
            return null;
        } catch (IOException e) {
            LOGGER.error("httpclient请求失败", e);
            return null;
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                    httpResponse.close();
                } catch (IOException e) {
                    LOGGER.error("关闭response失败", e);
                }
            }
        }
    }

}