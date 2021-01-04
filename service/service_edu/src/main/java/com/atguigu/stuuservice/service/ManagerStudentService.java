package com.atguigu.stuuservice.service;

import com.atguigu.stuuservice.entity.ManagerStudent;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-20
 */
public interface ManagerStudentService extends IService<ManagerStudent> {

    Map<String, Object> getTeacherFrontList(Page<ManagerStudent> pageTeacher);
}
