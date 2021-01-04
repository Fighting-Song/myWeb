package com.atguigu.stuuservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.stuuservice.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(name = "service-ucenter",fallback = UcenterClientFeignClient.class) //调用的服务名称
@Component
public interface UcenterClient {
    //根据token字符串获取用户信息
    //@PathVariable注解一定要指定参数名称，否则出错

    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);


}
