package com.atguigu.stuuservice.service;

import com.atguigu.stuuservice.entity.StuCourse;
import com.atguigu.stuuservice.entity.frontvo.CourseFrontVo;
import com.atguigu.stuuservice.entity.frontvo.CourseWebVo;
import com.atguigu.stuuservice.entity.vo.CourseInfoVo;
import com.atguigu.stuuservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
public interface StuCourseService extends IService<StuCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);
    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo getCoursePublishVoById(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<StuCourse> pageCourse, CourseFrontVo courseFrontVo);

    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);

    //查询某一天添加的事务数量
    Integer countCourseDay(String day);
}
