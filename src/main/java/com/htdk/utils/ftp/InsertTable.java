package com.htdk.utils.ftp;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class InsertTable {

	public static boolean work(String filePath, String sql,SqlProperties prop) {
		File orn = new File(filePath);
		File tmp[] = orn.listFiles();
		String fileName = "";
		for (int z = 0; z < tmp.length; z++) {
			ReadExcel er = new ReadExcel();
			String[] split = tmp[z].toString().split("\\\\");
			fileName = split[split.length - 1];
			try {
				if (fileName.equalsIgnoreCase("ALconBatchMasterData.xls")) {
					List<?> excelList = er.getValues(tmp[0].toString(), false);
					Connection conn = JDBCUtils.getConnection(prop);
					PreparedStatement state = conn.prepareStatement(sql);
					for (int i = 0; i < excelList.size(); i++) {
						List<?> list = (List<?>) excelList.get(i);
						for (int j = 0; j < list.size(); j++) {
							state.setString(j + 1, list.get(j).toString());
						}
						state.executeUpdate();
					}
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static boolean ediWork(String filePath, String sql, SqlProperties prop) {
		File orn = new File(filePath);
		File tmp[] = orn.listFiles();
		String fileName = "";
		for (int z = 0; z < tmp.length; z++) {
			ReadExcel er = new ReadExcel();
			String[] split = tmp[z].toString().split("\\\\");
			fileName = split[split.length - 1];
			try {
				if (fileName.equalsIgnoreCase("ALconDoNoData.xls")) {
					List<?> excelList = er.getValues(tmp[1].toString(), false);
					Connection conn = JDBCUtilsTMS.getConnection(prop);
					PreparedStatement state = conn.prepareStatement(sql);
					for (int i = 0; i < excelList.size(); i++) {
						List<?> list = (List<?>) excelList.get(i);
						for (int j = 0; j < list.size(); j++) {
							state.setString(j + 1, list.get(j).toString());
						}
						state.executeUpdate();
					}
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static void start(SqlProperties properties) {
		FtpUtils ftp = new FtpUtils();
		String path = System.getProperty("catalina.home");
//		String path = "";
		ftp.downloadFile("/Alcon", "ALconBatchMasterData.xls", path);
		ftp.downloadFile("/Alcon", "ALconDoNoData.xls", path);
		String sql = "INSERT INTO CUS_ALCON_ORDER (SKU,DESCR_C,DESCR_E,UOM,LOTATT04,LOTATT01,LOTATT02,LOTATT06,ADDTIME) VALUES (?,?,?,?,?,?,?,?,?)";
		String ediSql = "INSERT INTO EDI_HTDK_ALCON_NO (CUSTOMER_ORDER_NO,ALCON_NO,ADDWHO,ADDTIME) VALUES (?,?,?,?)";
		InsertTable.work(path, sql,properties);
		InsertTable.ediWork(path, ediSql,properties);
	}

}
