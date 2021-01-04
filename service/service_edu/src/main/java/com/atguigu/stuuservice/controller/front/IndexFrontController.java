package com.atguigu.stuuservice.controller.front;

import com.atguigu.commonutils.R;

import com.atguigu.stuuservice.entity.ManagerStudent;
import com.atguigu.stuuservice.entity.StuCourse;
import com.atguigu.stuuservice.service.ManagerStudentService;
import com.atguigu.stuuservice.service.StuCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stuuservice/indexfront")
//@CrossOrigin
public class IndexFrontController {

    @Autowired
    private StuCourseService courseService;

    @Autowired
    private ManagerStudentService managerStudentService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        //wrapper.orderByDesc("buyCount");
        wrapper.orderByDesc("buy_count");
        wrapper.last("limit 8");
        List<StuCourse> courseList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<ManagerStudent> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<ManagerStudent> studentList = managerStudentService.list(wrapperTeacher);

        return R.ok().data("courseList",courseList).data("studentList",studentList);
    }

}
