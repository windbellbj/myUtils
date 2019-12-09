package com.htdk.utils.ftp;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class InsertTable {

	public static void work(String filePath, String sql,SqlProperties properties) {
		File orn = new File(filePath);
		File tmp[] = orn.listFiles();
		for (int z = 0; z < tmp.length; z++) {
			ReadExcel er = new ReadExcel();
			List<?> excelList = er.getValues(tmp[z].toString(), false);
			try {
				Connection conn = JDBCUtils.getConnection(properties);
				PreparedStatement state = conn.prepareStatement(sql);
				if(excelList!=null){
					for (int i = 0; i < excelList.size(); i++) {
						List<?> list = (List<?>) excelList.get(i);
						for (int j = 0; j < list.size(); j++) {
							state.setString(j + 1, list.get(j).toString());
						}
						state.executeUpdate();
					}
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void start(SqlProperties properties) {
		FtpUtils ftp = new FtpUtils();
//		String path = System.getProperty("catalina.home");
		String path = System.getProperty("user.dir");
		ftp.downloadFile("/Alcon", "ALconBatchMasterData.xls", path);
		String sql = "INSERT INTO CUS_ALCON_ORDER (SKU,DESCR_C,DESCR_E,UOM,LOTATT04,LOTATT01,LOTATT02,LOTATT06,ADDTIME) VALUES (?,?,?,?,?,?,?,?,?)";
		InsertTable.work(path, sql,properties);
	}

}
