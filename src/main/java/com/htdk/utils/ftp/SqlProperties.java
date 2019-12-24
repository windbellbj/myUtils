package com.htdk.utils.ftp;

public class SqlProperties {
    public String driver;
    public String url;
    public String userName;
    public String passWord;
	public String urlTMS;

    public SqlProperties(String driver, String url, String userName, String passWord, String urlTMS) {
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
		this.urlTMS = urlTMS;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
	
	public String getUrlTMS() {
        return urlTMS;
    }

    public void setUrlTMS(String urlTMS) {
        this.urlTMS = urlTMS;
    }
}
