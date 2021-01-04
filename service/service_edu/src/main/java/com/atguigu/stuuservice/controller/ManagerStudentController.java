package com.atguigu.stuuservice.controller;


import com.atguigu.commonutils.R;

import com.atguigu.stuuservice.entity.ManagerStudent;
import com.atguigu.stuuservice.entity.vo.studentQuery;
import com.atguigu.stuuservice.service.ManagerStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-20
 */
@RestController
@RequestMapping("/stuuservice/manager-student")
//@CrossOrigin
public class ManagerStudentController {
    @Autowired
    ManagerStudentService managerStudentService;

    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<ManagerStudent> list = managerStudentService.list(null);
        return R.ok().data("items",list);
    }

    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id) {
        boolean flag = managerStudentService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 条件查询带分页的方法
    @PostMapping("pageStudentCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false)studentQuery studentQuery) {
        //创建page对象
        Page<ManagerStudent> pagestudent = new Page<>(current,limit);
        //构建条件
        QueryWrapper<ManagerStudent> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = studentQuery.getName();
        String adderss = studentQuery.getAddress();
        String begin = studentQuery.getBegin();
        String end = studentQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(adderss)) {
            //wrapper.eq("address",adderss);
            wrapper.like("address",adderss);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

         //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        //teacherService.page(pageTeacher,wrapper);
        managerStudentService.page(pagestudent,wrapper);
        long total = pagestudent.getTotal();//总记录数
        List<ManagerStudent> records = pagestudent.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //添加讲师接口的方法
    @PostMapping("addStudent")
    public R addTeacher(@RequestBody ManagerStudent managerStudent) {
        boolean save = managerStudentService.save(managerStudent);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        ManagerStudent managerStudent = managerStudentService.getById(id);
        return R.ok().data("student",managerStudent);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody ManagerStudent managerStudent) {
        boolean flag = managerStudentService.updateById(managerStudent);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}

