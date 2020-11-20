package com.example.order.service.impl;

import com.example.order.entity.GSysManager;
import com.example.order.entity.GSysUser;
import com.example.order.entity.GSysWorker;
import com.example.order.exception.LoginException;
import com.example.order.mapper.GSysManagerMapper;
import com.example.order.mapper.GSysUserMapper;
import com.example.order.mapper.GSysWorkerMapper;
import com.example.order.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    protected static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private GSysUserMapper gSysUserMapper;

    @Autowired
    private GSysManagerMapper gSysManagerMapper;

    @Autowired
    private GSysWorkerMapper gSysWorkerMapper;

    @Override
    public GSysUser doLogin(String username, String password) throws LoginException {
        GSysUser gSysUser = gSysUserMapper.loginUser(username);
        if (gSysUser != null){
            String passwordInDataBase = gSysUser.getPassword();
            //校验密码是否正确
            if (!StringUtils.equals(password,passwordInDataBase)){
                throw new LoginException("用户名或密码错误！");
            }
            //判断该用户状态
            if (StringUtils.equals(gSysUser.getStatus(),"0")){
                throw new LoginException("该用户已被禁用，请联系管理员！");
            }
            return gSysUser;
        }else {

            throw new LoginException("此用户不存在，请确认！！");

        }
    }

    @Override
    public GSysManager managerLogin(String username, String password) {
        GSysManager gSysManager = gSysManagerMapper.managerLogin(username);
        if (gSysManager != null){
            String passwordInDataBase = gSysManager.getPassword();
            //校验密码是否正确
            if (!StringUtils.equals(password,passwordInDataBase)){
                throw new LoginException("用户名或密码错误！");
            }
            //判断该用户状态
            if (StringUtils.equals(gSysManager.getIsValid(),"0")){
                throw new LoginException("该用户已被禁用，请联系管理员！");
            }
            return gSysManager;
        }else {
            throw new LoginException("此用户不存在，请确认！！");
        }
    }

    @Override
    public GSysWorker workerLogin(String username, String password) {
        GSysWorker gSysWorker = gSysWorkerMapper.workerLogin(username);
        if (gSysWorker != null){
            String passwordInDataBase = gSysWorker.getPassword();
            //校验密码是否正确
            if (!StringUtils.equals(password,passwordInDataBase)){
                throw new LoginException("用户名或密码错误！");
            }
            //判断该用户状态
            if (StringUtils.equals(gSysWorker.getIsValid(),"0")){
                throw new LoginException("该用户已被禁用，请联系管理员！");
            }
            return gSysWorker;
        }else {
            throw new LoginException("此用户不存在，请确认！！");
        }
    }
}
