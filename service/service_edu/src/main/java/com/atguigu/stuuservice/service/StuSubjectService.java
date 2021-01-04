package com.atguigu.stuuservice.service;

import com.atguigu.stuuservice.entity.StuSubject;
import com.atguigu.stuuservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 学院班级科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-27
 */
public interface StuSubjectService extends IService<StuSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file, StuSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
