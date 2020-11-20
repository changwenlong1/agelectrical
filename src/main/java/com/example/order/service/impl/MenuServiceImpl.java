package com.example.order.service.impl;

import com.example.order.entity.GSysMenu;
import com.example.order.entity.GSysUserMenu;
import com.example.order.exception.LoginException;
import com.example.order.mapper.GSysMenuMapper;
import com.example.order.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    protected static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private GSysMenuMapper GSysMenuMapper;

    @Override
    public boolean doUpdate(String menuId) throws LoginException {
        //删除
        GSysMenu GSysMenu = new GSysMenu();
        Long menuid = Long.parseLong(menuId);
        GSysMenu.setMenuId(menuid);
        GSysMenu.setIsValid("0");
        try {
            int count = GSysMenuMapper.updateByMenuId(GSysMenu);
            if(count >0){
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("删除失败：{}",e.getMessage());
            return false;
        }

    }

    @Override
    public List<GSysMenu> getMenuByMenuId(String menuId) {
        return GSysMenuMapper.getMenuByMenuId(menuId);
    }

    @Override
    public List<GSysMenu> getCompanyUserMenu() {
        return GSysMenuMapper.getCompanyUserMenu();
    }


    @Override
    public void doUpdateCompanyMenu(GSysUserMenu gSysUserMenu) {
        GSysMenuMapper.doUpdateCompanyMenu(gSysUserMenu);
    }

    @Override
    public void doUpdateMenu(GSysMenu gSysMenu) {
        GSysMenuMapper.doUpdateMenu(gSysMenu);
    }

    @Override
    public List<GSysMenu> getMenus() {
        return GSysMenuMapper.getMenus();
    }
}
