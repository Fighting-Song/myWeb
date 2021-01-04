package com.atguigu.stuuservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.stuuservice.client.VodClient;
import com.atguigu.stuuservice.entity.ManagerStudent;
import com.atguigu.stuuservice.entity.StuCourse;
import com.atguigu.stuuservice.entity.vo.CourseInfoVo;
import com.atguigu.stuuservice.entity.vo.CoursePublishVo;
import com.atguigu.stuuservice.entity.vo.studentQuery;
import com.atguigu.stuuservice.service.StuCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
@RestController
@RequestMapping("/stuuservice/stu-course")
//@CrossOrigin
public class StuCourseController {
    @Autowired
    private VodClient vodClient;
    @Autowired
    private StuCourseService courseService;
    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);
    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        StuCourse stuCourse = new StuCourse();
        stuCourse.setId(id);
        stuCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(stuCourse);
        return R.ok();
    }

    // 条件查询带分页的方法
    @PostMapping("pageStudentCourse/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) StuCourse stuCourse) {
        //创建page对象
        Page<StuCourse> pagestudent = new Page<>(current,limit);
        //构建条件
        QueryWrapper<StuCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String title = stuCourse.getTitle();
        String status = stuCourse.getStatus();
        //String begin = studentQuery.getBegin();
       // String end = studentQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            //wrapper.eq("address",adderss);
            wrapper.like("status",status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        courseService.page(pagestudent,wrapper);
        long total = pagestudent.getTotal();//总记录数
        List<StuCourse> records = pagestudent.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //删除事务
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        StuCourse stuCourse = courseService.getById(courseId);
        String id = stuCourse.getVideoSourceId();
        if(!StringUtils.isEmpty(id)){
            vodClient.removeAlyVideo(id);
        }

        courseService.removeCourse(courseId);
      return R.ok();
    }

    //查询某一天添加的事务数量
    @GetMapping("countCourse/{day}")
    public R countCourse(@PathVariable String day) {
        Integer count = courseService.countCourseDay(day);
        return R.ok().data("countCourse",count);
    }
}

