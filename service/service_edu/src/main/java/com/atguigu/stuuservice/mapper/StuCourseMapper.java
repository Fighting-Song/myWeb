package com.atguigu.stuuservice.mapper;

import com.atguigu.stuuservice.entity.StuCourse;
import com.atguigu.stuuservice.entity.frontvo.CourseWebVo;
import com.atguigu.stuuservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-11-30
 */
public interface StuCourseMapper extends BaseMapper<StuCourse> {
    CoursePublishVo getPublishCourseInfo(String id);

    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);

    //查询某一天添加的事务数量
    Integer countCourseDay(String day);
}
