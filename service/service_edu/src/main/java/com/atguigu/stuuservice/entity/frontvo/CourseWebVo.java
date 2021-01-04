package com.atguigu.stuuservice.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CourseWebVo {

    private String id;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer Num;

    @ApiModelProperty(value = "总课时")
    private String classname;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    /*@ApiModelProperty(value = "浏览数量")
    private Long viewCount;*/

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "讲师ID")
    private String studentId;

    @ApiModelProperty(value = "讲师姓名")
    private String studentName;

    @ApiModelProperty(value = "发布修改时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "讲师头像")
    private String image;

    @ApiModelProperty(value = "课程一级类别ID")
    private String subjectLevelOneId;

    @ApiModelProperty(value = "类别一级名称")
    private String subjectLevelOne;

    @ApiModelProperty(value = "课程二级类别ID")
    private String subjectLevelTwoId;

    @ApiModelProperty(value = "类别二级名称")
    private String subjectLevelTwo;

    @ApiModelProperty(value = "视频id")
    private String videoSourceId;
}
