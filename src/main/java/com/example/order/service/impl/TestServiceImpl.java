package com.example.order.service.impl;

import com.example.order.entity.GSysMenu;
import com.example.order.mapper.GSysMenuMapper;
import com.example.order.mapper.GSysUserMapper;
import com.example.order.result.PageResult;
import com.example.order.service.TestService;
import com.example.order.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    protected static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private GSysUserMapper gSysUserMapper;

    @Autowired
    private GSysMenuMapper gSysMenuMapper;


    @Override
    public List<GSysMenu> getMenus(Long userId) {
        return gSysMenuMapper.getMenuListForUserId(userId);
    }

    @Override
    public PageResult findPage(Long userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GSysMenu> lists = gSysMenuMapper.getMenuListForUserId(userId);
        PageInfo<GSysMenu> gSysMenuPageInfo = new PageInfo<>(lists);
        return PageUtils.getPageResult(gSysMenuPageInfo);
    }
}
