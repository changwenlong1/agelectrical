package com.example.order.service;

import com.example.order.entity.GSysCompany;
import com.example.order.exception.AddUserException;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    /**
     * 通过单位id查询单位
     * @param companyId id
     * @return
     */
    GSysCompany getList(Long companyId);

    /**
     * 查询全部单位
     * @return
     */
    List<GSysCompany> getInfo();

    /**
     * 添加单位
     * @return
     */
    void addInfo(GSysCompany gSysManage)  throws AddUserException;

    /**
     * 修改单位
     * @return
     */
    void updateList(GSysCompany gSysManage);

    /**
     * “删除”单位
     * @return
     */
    void doDelete(String companyId);

    /**
     * 查询全部单位下拉
     * @return
     */
    List<Map<String, Object>> getCompanyList();
}
