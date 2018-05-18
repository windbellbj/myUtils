package com.lxl.utils.poi.test;

import com.lxl.utils.poi.domain.Student;
import com.lxl.utils.poi.util.ExportExcel;
import com.lxl.utils.poi.util.ImportExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther lixinlong
 * @create 2018/5/18
 */

public class TestPoi {

    @Test
    public void testExport(){
        try {
            List<Student> students = new ArrayList<>();
            students.add(new Student(1,"小一","stu001",new Date(),new Date(),1));
            students.add(new Student(2,"小二","stu002",new Date(),new Date(),0));
            students.add(new Student(3,"小三","stu003",new Date(),new Date(),1));
            /**
             * 功能: 导出为Excel工作簿
             * 参数: sheetName[工作簿中的一张工作表的名称]
             * 参数: titleName[表格的标题名称]
             * 参数: headers[表格每一列的列名]
             * 参数: dataSet[要导出的数据源]
             * 参数: resultUrl[导出的excel文件地址]
             * 参数: pattern[时间类型数据的格式]
             */
            String sheetName = "学生信息表";
            String titleName = "学生列表";
            String headers[] = {"id","姓名","学号","入学时间","退学时间","状态"};
            String resultUrl = "/users/lixinlong/wk/student.xlsx";
            String pattern="yyyy-MM-dd";
            ExportExcel.exportExcel(sheetName,titleName,headers,students,resultUrl,pattern);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void importExcel()
    {
        try {
            String originUrl="/users/lixinlong/wk/student.xlsx";
            int startRow=2;
            int endRow=0;
            /***
             * 参数: originUrl[Excel表的所在路径]
             * 参数: startRow[从第几行开始]
             * 参数: endRow[到第几行结束
             *                  (0表示所有行;
             *                  正数表示到第几行结束;
             *                  负数表示到倒数第几行结束)]
             * 参数: clazz[要返回的对象集合的类型]
             */
            List<Student> students = (List<Student>) ImportExcel.importExcel(originUrl, startRow, endRow, Student.class);
            for (Student student : students) {
                System.out.println(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
