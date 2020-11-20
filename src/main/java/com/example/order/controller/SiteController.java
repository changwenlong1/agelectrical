package com.example.order.controller;


import com.example.order.common.Constant;
import com.example.order.entity.GSysPara;
import com.example.order.exception.LoginException;
import com.example.order.service.SiteService;
import com.example.order.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/site")
public class SiteController {

    @Autowired
    SiteService siteService;

    @RequestMapping(value = "/getSiteList", method = RequestMethod.POST)
    public void addList(@RequestParam("userId") Long userId,
                        HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<GSysPara> list = siteService.getList(userId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, list);
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response, res);
        }

    }

    @RequestMapping(value = "/openByParaId", method = RequestMethod.POST)
    public void selectOpenByPrimaryKey(@RequestParam("paraId") Long paraId,
                                       HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            boolean b = siteService.selectOpenByPrimaryKey(paraId);
            if (b) {
                res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
                ServletUtils.writeToResponse(response, res);
            } else {
                res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
                ServletUtils.writeToResponse(response, res);
            }
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response, res);
        }

    }

    @RequestMapping(value = "/updateRemark", method = RequestMethod.POST)
    public void selectContentByPrimaryKey(@RequestParam("paraId") Long paraId,
                                       @RequestParam("mescontent") String mescontent,
                                       HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            boolean b = siteService.selectContentByPrimaryKey(paraId,mescontent);
            if (b) {
                res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
                ServletUtils.writeToResponse(response, res);
            } else {
                res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
                ServletUtils.writeToResponse(response, res);
            }
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response, res);
        }

    }

}
