package com.example.order.service;

import com.example.order.entity.GSysPara;

import java.util.List;

public interface SiteService {

    List<GSysPara> getList(Long userId);

    boolean selectOpenByPrimaryKey(Long paraId);

    boolean selectContentByPrimaryKey(Long paraId,String remark);


}
