package com.example.order.service.impl;

import com.example.order.entity.GSysMenu;
import com.example.order.entity.GSysUser;
import com.example.order.exception.AddUserException;
import com.example.order.mapper.GSysMenuMapper;
import com.example.order.mapper.GSysUserMapper;
import com.example.order.request.AddUserRequestParam;
import com.example.order.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    protected static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${sysPara.defaultPassWord}")
    private String defaultPassWord;

    @Autowired
    private GSysMenuMapper gSysMenuMapper;

    @Autowired
    private GSysUserMapper gSysUserMapper;

    @Override
    public Map<String,Object> getMenuList(Long userId) {
        List<GSysMenu> menuList = gSysMenuMapper.getMenuListForUserId(userId);
        Map<String,Object> result = new HashMap();
        for (GSysMenu gSysMenu : menuList) {
            if (gSysMenu.getParentId() == 0){
                Map<String,Object> menus = new HashMap();
                List<GSysMenu> list = gSysMenuMapper.getMenuListForParentId(gSysMenu.getMenuId());
                for (GSysMenu sysMenu : list) {
                    menus.put(sysMenu.getRemark(),sysMenu.getIsValid());
                }
                result.put(gSysMenu.getRemark(),menus);
            }
        }
        return result;
    }

    @Override
    public GSysUser getMenuListByLoginName(String loginName) {
        return gSysUserMapper.loginUser(loginName);
    }

    @Override
    public void addUser(AddUserRequestParam addUserRequestParam) throws AddUserException{
        GSysUser gSysUser = new GSysUser();
        gSysUser.setLoginName(addUserRequestParam.getUsername());
        gSysUser.setPassword(defaultPassWord);
        gSysUser.setStatus("1");
        gSysUser.setCreateTime(new Date());
        try {
            gSysUserMapper.insertSelective(gSysUser);
        } catch (Exception e) {
            logger.error("新增用户失败!");
            throw new AddUserException("新增用户失败");
        }

    }

    @Override
    public long addCompanyUser(GSysUser gSysUser) throws AddUserException {
        try {
            gSysUserMapper.insertSelective(gSysUser);
            long userid = gSysUser.getUserId();
            return userid;
        } catch (Exception e) {
            logger.error("新增用户失败!");
            throw new AddUserException("新增用户失败");
        }
    }

    @Override
    public void updateCompanyUserStatus(GSysUser gSysUser) {
        gSysUserMapper.updateCompanyUserStatus(gSysUser);
    }



}
