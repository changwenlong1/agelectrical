package com.example.order.service.impl;

import com.example.order.entity.GSysCompany;
import com.example.order.exception.AddUserException;
import com.example.order.mapper.GSysCompanyMapper;
import com.example.order.service.CompanyService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    protected static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private GSysCompanyMapper gSysCompanyMapper;

    @Override
    public GSysCompany getList(@Param("companyId")Long companyId) {
        return gSysCompanyMapper.getList(companyId);

    }

    @Override
    public List<GSysCompany> getInfo() {
        return gSysCompanyMapper.getInfo();

    }
    @Override
    public List<Map<String,Object>> getCompanyList() {
        return gSysCompanyMapper.getCompanyList();

    }

    @Override
    public void addInfo(GSysCompany gSysManage) throws AddUserException {
        try {
            gSysCompanyMapper.insertSelective(gSysManage);
        } catch (Exception e) {
            logger.error("新增失败!");
            throw new AddUserException("新增失败");
        }
    }

    @Override
    public void updateList(GSysCompany gSysManage) {
        try {
            gSysCompanyMapper.updateByPrimaryKeySelective(gSysManage);
        } catch (Exception e) {
            logger.error("修改失败!");
            throw new AddUserException("修改失败");
        }
    }


    @Override
    public void doDelete(String companyId) {
        try {
            gSysCompanyMapper.updateIsdelById(companyId);
        } catch (Exception e) {
            logger.error("删除失败!");
            throw new AddUserException("删除失败");
        }
    }
}
