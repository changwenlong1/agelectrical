package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.exception.LoginException;

import com.example.order.service.OrderService;
import com.example.order.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName PcCompanyController
 * @Description 订单Controller
 * @Author changwl
 * @Date 2020/9/24
 * @Version 1.0
 */
@Controller
@RequestMapping("/api/order")
public class OrderController {

    protected static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> gSysOrderList= orderService.getList();
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysOrderList);
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

}
