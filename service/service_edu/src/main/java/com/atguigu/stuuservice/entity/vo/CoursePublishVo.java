package com.atguigu.stuuservice.entity.vo;

import lombok.Data;

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer num;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String studentName;
    private String price;//只用于显示
  private String description;
}
