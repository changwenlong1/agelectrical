package com.example.order.service;

import com.example.order.entity.GSysMenu;
import com.example.order.result.PageResult;

import java.util.List;

public interface TestService {

    /**
     * 获取用户菜单权限
     * @param userId
     * @return
     */
    List<GSysMenu> getMenus(Long userId);


    PageResult findPage(Long userId,Integer pageNum,Integer pageSize);

}
