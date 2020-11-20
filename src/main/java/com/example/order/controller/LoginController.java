package com.example.order.controller;

import com.example.order.common.Constant;
import com.example.order.entity.GSysManager;
import com.example.order.entity.GSysUser;
import com.example.order.entity.GSysWorker;
import com.example.order.exception.LoginException;
import com.example.order.service.LoginService;
import com.example.order.service.TestService;
import com.example.order.utils.ServletUtils;
import com.example.order.utils.TokenUtil;
import com.example.order.vo.ValidateCodeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description 用户登录Controller
 * @Author xionggy
 * @Date 2020/8/27
 * @Version 1.0
 */
@Controller
//@CrossOrigin(origins = { "*" }, allowedHeaders = { "*" }, allowCredentials = "true", methods = { RequestMethod.POST })
@RequestMapping("/api/login")
public class LoginController {

    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    TestService testService;

    @Autowired
    LoginService loginService;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @Value("${sysPara.isTest}")
    private String isTest;


    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, "成功!");
        ServletUtils.writeToResponse(response, res);
    }

    /**
     * 生成图片验证码
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getPicVercode")
    public void generate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        ValidateCodeVo vCode = new ValidateCodeVo(120, 50, 4, 50);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        response.getOutputStream().flush();
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public void doLogin(@RequestParam("userName") String userName,
                          @RequestParam("passWord") String passWord,
                          @RequestParam(value = "loginCode",required = false) String loginCode,
                          HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> res = new HashMap<String, Object>();

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)){
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "请输入用户名或密码!");
            ServletUtils.writeToResponse(response, res);
            return;
        }

        //判断当前环境是否为为开发环境,开发环境不做验证码检验
//        if (!"dev".equals(isTest)) {
//            //校验验证码
//            Map<String, Object> result = checkCode(loginCode, request.getAttribute("code"));
//            if (400 == Integer.parseInt(result.get(Constant.RESPONSE_CODE).toString())){
//                ServletUtils.writeToResponse(response, res);
//            }
//        }

        try {
            GSysUser gSysUser = loginService.doLogin(userName, passWord);

            //根据用户信息生成token
            String token = TokenUtil.sign(gSysUser);
            gSysUser.setToken(token);

            request.getSession().setAttribute(Constant.SESSION_SYSUSER, gSysUser);
            res.put(Constant.RESPONSE_DATA, gSysUser);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "登录成功!");
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

    public Map<String, Object> checkCode(String loginCode, Object sessionCode){
        Map<String, Object> res = new HashMap<String, Object>();
        if (StringUtils.isBlank(loginCode)){
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "请输入验证码!");
            return res;
        }
        if (loginCode.length() != 4 || !loginCode.equals(sessionCode)){
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "验证码输入错误!");
            return res;
        }
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, "验证通过!");
        return res;
    }

    /**
     * 小程序端客户经理登录
     * @param gSysManager
     * @param request
     * @param response
     */
    @RequestMapping(value = "/vchart/manager/doLogin",method = RequestMethod.POST)
    public void managerLogin(@RequestBody GSysManager gSysManager,
                          HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> res = new HashMap<String, Object>();
        String userName = gSysManager.getPhone();
        String passWord = gSysManager.getPassword();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)){
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "请输入用户名或密码!");
            ServletUtils.writeToResponse(response, res);
            return;
        }

        try {
            GSysManager gSysManagers = loginService.managerLogin(userName, passWord);

            request.getSession().setAttribute(Constant.SESSION_SYSUSER, gSysManagers);
            res.put(Constant.RESPONSE_DATA, gSysManagers);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "登录成功!");
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

    /**
     * 小程序端维修工登录
     * @param gSysWorker
     * @param request
     * @param response
     */
    @RequestMapping(value = "/vchart/worker/doLogin",method = RequestMethod.POST)
    public void workerLogin(@RequestBody GSysWorker gSysWorker,
                             HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> res = new HashMap<String, Object>();
        String userName = gSysWorker.getPhone();
        String passWord = gSysWorker.getPassword();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)){
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "请输入用户名或密码!");
            ServletUtils.writeToResponse(response, res);
            return;
        }
        try {
            GSysWorker gSysWorkers = loginService.workerLogin(userName, passWord);
            request.getSession().setAttribute(Constant.SESSION_SYSUSER, gSysWorkers);
            res.put(Constant.RESPONSE_DATA, gSysWorkers);
            res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "登录成功!");
            ServletUtils.writeToResponse(response, res);
        } catch (LoginException e) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, e.getMessage());
            ServletUtils.writeToResponse(response, res);
        }

    }

}
