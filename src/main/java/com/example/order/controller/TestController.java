package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.entity.GSysMenu;
import com.example.order.entity.GSysUser;
import com.example.order.result.PageResult;
import com.example.order.service.TestService;
import com.example.order.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("index")
    public String testOrder(){
        return "index";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public void dologin(HttpServletRequest request, HttpServletResponse response,
                        @RequestBody GSysUser gSysUser){
        Map<String, Object> res = new HashMap<String, Object>();
        //todo 此处写登录逻辑
        res.put(Constant.RESPONSE_DATA, "success");
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        ServletUtils.writeToResponse(response,res);
    }

    @RequestMapping(value = "/getMenus",method = RequestMethod.GET)
    public void getMenus(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("userId") Long userId){
        Map<String, Object> res = new HashMap<String, Object>();
        List<GSysMenu> menus = testService.getMenus(userId);
        res.put(Constant.RESPONSE_DATA, menus);
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        ServletUtils.writeToResponse(response,res);
    }

    @RequestMapping(value = "/getMenusForTest",method = RequestMethod.GET)
    public void getMenusForTest(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam("userId") Long userId,
                                @RequestParam("pageNum") Integer pageNum,
                                @RequestParam("pageSize") Integer pageSize){
        Map<String, Object> res = new HashMap<String, Object>();
        PageResult page = testService.findPage(userId, pageNum, pageSize);
        res.put(Constant.RESPONSE_DATA, page);
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        ServletUtils.writeToResponse(response,res);
    }
}
