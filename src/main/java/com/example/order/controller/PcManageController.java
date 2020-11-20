package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.entity.GSysManage;
import com.example.order.exception.LoginException;
import com.example.order.service.ManageService;
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
import java.util.List;
import java.util.Map;

/**
 * @ClassName ManageController
 * @Description 公告,新闻，轮播图Controller
 * @Author xionggy
 * @Date 2020/9/10
 * @Version 1.0
 */
@Controller
@RequestMapping("/api/manage")
public class PcManageController {

    protected static final Logger logger = LoggerFactory.getLogger(PcManageController.class);

    @Autowired
    ManageService manageService;

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public void getList(@RequestParam("objId") Long objId,
                            @RequestParam("type") String type,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            GSysManage gSysManage= manageService.getInfo(objId,type);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysManage);
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public void getInfo(@RequestParam("type") String type,@RequestParam(value="searchContent",required=false)String searchContent,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<GSysManage> gSysManage= manageService.getList(type,searchContent);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysManage);
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

    @RequestMapping(value = "/getNewsList",method = RequestMethod.POST)
    public void getNewsList(@RequestParam("type") String type,@RequestParam(value="searchContent",required=false)String searchContent,
                        HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<Map<String, Object>> gSysManage= manageService.getNewsList(type,searchContent);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysManage);
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

    @RequestMapping(value = "/addList",method = RequestMethod.POST)
    public void addList(@RequestBody GSysManage gSysManage,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            manageService.addInfo(gSysManage);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }

    }

    @RequestMapping(value = "/updateList",method = RequestMethod.POST)
    public void updateList(@RequestBody GSysManage gSysManage,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            manageService.updateList(gSysManage);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }

    }
    @RequestMapping(value = "/doDelete",method = RequestMethod.POST)
    public void doDelete(@RequestParam("objId") String objId,
                         HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            manageService.doDelete(objId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }
    }
    @RequestMapping(value = "/examineById",method = RequestMethod.POST)
    public void selectByOId(@RequestParam("objId") Long objId,
                            @RequestParam("exaId") String exaId,
                            @RequestParam("remark") String remark,
                         HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            boolean b = manageService.reviewById(objId, exaId, remark);
            if (b){
                res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
                ServletUtils.writeToResponse(response, res);
            }else {
                res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
                ServletUtils.writeToResponse(response, res);
            }
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }
    }
    @RequestMapping(value = "/releaseById",method = RequestMethod.POST)
    public void selectByOId(@RequestParam("objId") Long objId,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            boolean b = manageService.releaseById(objId);
            if (b){
                res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
                ServletUtils.writeToResponse(response, res);
            }else {
                res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
                ServletUtils.writeToResponse(response, res);
            }
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }
    }
}
