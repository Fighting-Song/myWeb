package com.atguigu.stuuservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ManagerStudent对象", description="")
public class ManagerStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "id", type = IdType.ID_WORKER_STR)//mp自带的主键策略,会生成19位值,数字类型使用
    @TableId(value = "id", type = IdType.ID_WORKER_STR)//mp自带的主键策略,会生成19位值,字符串类型使用
    private String id;

    private String name;

    private String sex;

    private String classname;

    private String tel;

    private String image;

    private String address;

    private String idcard;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableLogic
    //@TableField(fill = FieldFill.INSERT)
    private Integer idDeleted;

    private String dormitory;

    private String things;
}
