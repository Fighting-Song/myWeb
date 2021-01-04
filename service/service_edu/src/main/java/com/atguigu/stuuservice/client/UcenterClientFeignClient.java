package com.atguigu.stuuservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.stuuservice.entity.UcenterMember;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Component
public class UcenterClientFeignClient implements UcenterClient{


    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        return null;
    }
}
