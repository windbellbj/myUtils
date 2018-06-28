package com.lxl.utils.redis;

import com.lxl.utils.poi.domain.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <dl>
 * <dt>TestwController</dt>
 * <dd>@auther lixinlong</dd>
 * <dd>@create 2018/6/28</dd>
 * <dd></dd>
 * </dl>
 */
@RestController
@RequestMapping("redis")
public class TestController {

    @Autowired
    private RedisDBHelper redisDBHelper;

    @RequestMapping("/insert")
    public void insert(){
        Student student = new Student(1,"yi","S01",new Date(),new Date(),1);

        Long s01 = redisDBHelper.listPush("S01", student);

        System.out.println(s01);
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudent(){
        List<Student> s01 = redisDBHelper.listFindAll("S01");
        for (Student student : s01) {
            System.out.println(student);
        }
        return ResponseEntity.ok(s01);
    }
}
