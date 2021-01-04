package com.atguigu.stuuservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.stuuservice.client.OrdersClient;
import com.atguigu.stuuservice.entity.StuCourse;
import com.atguigu.stuuservice.entity.frontvo.CourseFrontVo;
import com.atguigu.stuuservice.entity.frontvo.CourseWebVo;
import com.atguigu.stuuservice.service.StuCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/stuuservice/coursefront")
//@CrossOrigin
public class CourseFrontController {

    @Autowired
    private StuCourseService courseService;

    @Autowired
    private OrdersClient ordersClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<StuCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        System.out.println(map);
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId) {
        //根据课程id，编写sql语句查询课程信息
        StuCourse course = courseService.getById(courseId);
        Long count = course.getBuyCount();
        Long count1 = count+1;
        course.setBuyCount(count1);
        courseService.saveOrUpdate(course);
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id和用户id查询当前课程是否已经支付过了
       // boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseWebVo",courseWebVo);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }

}












