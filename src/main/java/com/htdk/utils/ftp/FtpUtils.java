package com.htdk.utils.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

public class FtpUtils {

	// ftp服务器地址
	public String hostname = "47.102.143.188";
	// ftp服务器端口号
	public Integer port = 21;
	// ftp登录账号
	public String username = "Report";
	// ftp登录密码
	public String password = "htdk2019";

	public FTPClient ftpClient = null;

	/**
	 * 初始化ftp服务器
	 */
	public void initFtpClient() {
		ftpClient = new FTPClient();
		ftpClient.setControlEncoding("utf-8");
		try {
			System.out.println("connecting...ftp服务器:" + this.hostname + ":" + this.port);
			ftpClient.connect(hostname, port); // 连接ftp服务器
			ftpClient.login(username, password); // 登录ftp服务器
			int replyCode = ftpClient.getReplyCode(); // 是否成功登录服务器
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("connect failed...ftp服务器:" + this.hostname + ":" + this.port);
			}
			System.out.println("connect successfu...ftp服务器:" + this.hostname + ":" + this.port);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean downloadFile(String pathname, String filename, String localpath) {
		boolean flag = false;
		OutputStream os = null;
		try {
			System.out.println("开始下载文件");
			initFtpClient();
			// 切换FTP目录
			boolean changeFlag = ftpClient.changeWorkingDirectory(pathname);
			System.err.println("changeFlag==" + changeFlag);

			ftpClient.enterLocalPassiveMode();
			ftpClient.setRemoteVerificationEnabled(false);
			// 查看有哪些文件夹 以确定切换的ftp路径正确
			String[] a = ftpClient.listNames();
			System.err.println(a[0]);

			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + file.getName());
					os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			ftpClient.logout();
			flag = true;
			System.out.println("下载文件成功");
		} catch (Exception e) {
			System.out.println("下载文件失败");
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		FtpUtils ftp = new FtpUtils();
		ftp.downloadFile("/Alcon", "ALconBatchMasterData.xls", "D:\\");
	}
}
