package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.entity.GSysCompany;
import com.example.order.entity.GSysMenu;
import com.example.order.entity.GSysUser;
import com.example.order.entity.GSysUserMenu;
import com.example.order.exception.LoginException;
import com.example.order.service.CompanyService;
import com.example.order.service.MenuService;
import com.example.order.service.UserService;
import com.example.order.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PcCompanyController
 * @Description 单位Controller
 * @Author xionggy
 * @Date 2020/9/10
 * @Version 1.0
 */
@Controller
@RequestMapping("/api/company")
public class PcCompanyController {

    protected static final Logger logger = LoggerFactory.getLogger(PcCompanyController.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Value("${sysPara.defaultPassWord}")
    private String defaultPassWord;

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public void getList(@RequestParam("companyId") Long companyId,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            GSysCompany gSysCompany= companyService.getList(companyId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysCompany);
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            List<GSysCompany> gSysCompany= companyService.getInfo();
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_DATA, gSysCompany);
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

    @RequestMapping(value = "/addList",method = RequestMethod.POST)
    public void addList(@RequestBody GSysCompany gSysCompany,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            GSysUser gSysUser = new GSysUser();
            gSysUser = userService.getMenuListByLoginName(gSysCompany.getUserTel());
            if(gSysUser != null){
                res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                res.put(Constant.RESPONSE_CODE_MSG, "此手机号已被使用，操作失败!");
            }else{
            companyService.addInfo(gSysCompany);
            GSysUser gSysUser1 = new GSysUser();
            gSysUser1.setCreateTime(new Date());
            gSysUser1.setLoginName(gSysCompany.getUserTel());
            gSysUser1.setUserName(gSysCompany.getUserName());
            gSysUser1.setPassword(defaultPassWord);
            gSysUser1.setStatus("1");
            long userId = userService.addCompanyUser(gSysUser1);
            List<GSysMenu> gSysMenuList = menuService.getCompanyUserMenu();
            GSysUserMenu gSysUserMenu = new GSysUserMenu();
            for (int i = 0;i<gSysMenuList.size();i++){
                gSysUserMenu.setUserId(userId);
                gSysUserMenu.setMenuId(gSysMenuList.get(i).getMenuId());
                menuService.doUpdateCompanyMenu(gSysUserMenu);
            }
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
            }
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }

    }

    @RequestMapping(value = "/updateList",method = RequestMethod.POST)
    public void updateList(@RequestBody GSysCompany gSysCompany,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            companyService.updateList(gSysCompany);
            GSysUser gSysUser = new GSysUser();
            gSysUser.setLoginName(gSysCompany.getUserTel());
            gSysUser.setStatus(gSysCompany.getIsValid());
            userService.updateCompanyUserStatus(gSysUser);
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
    public void doDelete(@RequestParam("companyId") String companyId,
                         HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            companyService.doDelete(companyId);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作成功!");
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "操作失败!");
            ServletUtils.writeToResponse(response,res);
        }
    }
}
