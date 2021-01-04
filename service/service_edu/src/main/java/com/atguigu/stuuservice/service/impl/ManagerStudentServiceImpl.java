package com.atguigu.stuuservice.service.impl;

import com.atguigu.stuuservice.entity.ManagerStudent;
import com.atguigu.stuuservice.mapper.ManagerStudentMapper;
import com.atguigu.stuuservice.service.ManagerStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-20
 */
@Service
public class ManagerStudentServiceImpl extends ServiceImpl<ManagerStudentMapper, ManagerStudent> implements ManagerStudentService {

    @Override
    public Map<String, Object> getTeacherFrontList(Page<ManagerStudent> pageTeacher) {
        QueryWrapper<ManagerStudent> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //把分页数据封装到pageTeacher对象
        baseMapper.selectPage(pageTeacher,wrapper);

        List<ManagerStudent> records = pageTeacher.getRecords();
        long current = pageTeacher.getCurrent();
        long pages = pageTeacher.getPages();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        boolean hasNext = pageTeacher.hasNext();//下一页
        boolean hasPrevious = pageTeacher.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }
}
