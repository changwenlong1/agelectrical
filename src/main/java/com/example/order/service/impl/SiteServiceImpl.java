package com.example.order.service.impl;

import com.example.order.entity.GSysPara;
import com.example.order.mapper.GSysParaMapper;
import com.example.order.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {
    protected static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private GSysParaMapper gSysParaMapper;


    @Override
    public List<GSysPara> getList(Long userId) {
        return gSysParaMapper.getList(userId);

    }

    @Override
    public boolean selectOpenByPrimaryKey(Long paraId) {
        try {
            GSysPara gSysPara = gSysParaMapper.selectByPrimaryKey(paraId);
            if (gSysPara.getValue().equals("1")) {
                gSysPara.setValue("0");
                gSysParaMapper.updateByPrimaryKeySelective(gSysPara);
            } else {
                gSysPara.setValue("1");
                gSysParaMapper.updateByPrimaryKeySelective(gSysPara);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean selectContentByPrimaryKey(Long paraId, String mescontent) {
        try {
            GSysPara gSysPara = gSysParaMapper.selectByPrimaryKey(paraId);
            gSysPara.setValue(mescontent);
            gSysParaMapper.updateByPrimaryKeySelective(gSysPara);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
