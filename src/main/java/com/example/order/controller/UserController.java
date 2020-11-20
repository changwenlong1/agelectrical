package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.exception.AddUserException;
import com.example.order.request.AddUserRequestParam;
import com.example.order.service.UserService;
import com.example.order.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 用户信息管理Controller
 * @Author xionggy
 * @Date 2020/8/27
 * @Version 1.0
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getMenuList",method = RequestMethod.GET)
    public void getMenuList(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("userId") Long userId){
        Map<String, Object> res = new HashMap<String, Object>();
        Map<String, Object> menuList = userService.getMenuList(userId);
        res.put(Constant.RESPONSE_DATA, menuList);
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        ServletUtils.writeToResponse(response,res);
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserRequestParam addUserRequestParam, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            userService.addUser(addUserRequestParam);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
            ServletUtils.writeToResponse(response,res);
        } catch (AddUserException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }
    }

}
