package com.atguigu.stuuservice.controller.front;

import com.atguigu.commonutils.R;

import com.atguigu.stuuservice.entity.ManagerStudent;
import com.atguigu.stuuservice.entity.StuCourse;

import com.atguigu.stuuservice.entity.vo.CourseInfoVo;
import com.atguigu.stuuservice.service.ManagerStudentService;
import com.atguigu.stuuservice.service.StuCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stuuservice/teacherfront")
//@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private ManagerStudentService managerStudentService;

    @Autowired
    private StuCourseService stuCourseService;

    //1 分页查询讲师的方法
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<ManagerStudent> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = managerStudentService.getTeacherFrontList(pageTeacher);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //2 讲师详情的功能
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        //1 根据讲师id查询讲师基本信息
        ManagerStudent student = managerStudentService.getById(teacherId);
        System.out.println(student);
        //2 根据讲师id查询所讲课程
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<StuCourse> courseList = stuCourseService.list(wrapper);
        System.out.println(courseList);
        return R.ok().data("student",student).data("courseList",courseList);
    }
}












