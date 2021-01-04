package com.atguigu.stuuservice.controller.front;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.stuuservice.client.UcenterClient;
import com.atguigu.stuuservice.entity.StuComment;
import com.atguigu.stuuservice.entity.UcenterMember;
import com.atguigu.stuuservice.service.StuCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-29
 */
@RestController
@RequestMapping("/stuuservice/stu-comment")
//@CrossOrigin
public class StuCommentController {
    @Autowired
    private StuCommentService commentService;
    @Autowired
    private UcenterClient ucenterClient;

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    String courseId) {
        Page<StuComment> pageParam = new Page<>(page, limit);

        QueryWrapper<StuComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_create");

        commentService.page(pageParam,wrapper);
        List<StuComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public R save(@RequestBody StuComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        StuComment comment1 =new StuComment();
        comment1.setCourseId(comment.getCourseId());
        comment1.setMemberId(memberId);
       // System.out.println(memberId);
        comment1.setContent(comment.getContent());
        UcenterMemberOrder ucenterInfo = ucenterClient.getUserInfoOrder(memberId);

       // System.out.println(ucenterInfo.getId());
        comment1.setTeacherId(ucenterInfo.getId());
        comment1.setNickname(ucenterInfo.getNickname());
        comment1.setAvatar(ucenterInfo.getAvatar());

        commentService.save(comment1);
        return R.ok();
    }
}

