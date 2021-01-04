package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface CourseClient {
    //查询某一天添加的事务数量
    @GetMapping("/stuuservice/stu-course/countCourse/{day}")
    public R countCourse(@PathVariable("day") String day);
}
