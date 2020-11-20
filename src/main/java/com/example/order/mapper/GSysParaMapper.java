package com.example.order.mapper;

import com.example.order.entity.GSysPara;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GSysParaMapper {

    GSysPara deleteByPrimaryKey(Long paraId);


    int insert(GSysPara record);


    int insertSelective(GSysPara record);


    GSysPara selectByPrimaryKey(Long paraId);


    int updateByPrimaryKeySelective(GSysPara record);


    int updateByPrimaryKey(GSysPara record);

    List<GSysPara> getList(Long userId);
}