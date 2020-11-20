package com.example.order.mapper;

import com.example.order.entity.GSysUserMenu;
import org.springframework.stereotype.Component;

@Component
public interface GSysUserMenuMapper {

    int insertSelective(GSysUserMenu record);
}
