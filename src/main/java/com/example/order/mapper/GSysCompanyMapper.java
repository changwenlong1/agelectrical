package com.example.order.mapper;

import com.example.order.entity.GSysCompany;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface GSysCompanyMapper {
    /**
     * 添加单位
     * @param record
     * @return
     */
    int insertSelective(GSysCompany record);

    /**
     * 修改单位
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GSysCompany record);

    /**
     * “删除”单位
     * @param companyId
     * @return
     */
    void updateIsdelById(String companyId);

    /**
     * 获取所有单位
     * @return
     */
    List<GSysCompany> getInfo();

    /**
     * 获取单位下拉
     * @return
     */
    List<Map<String,Object>> getCompanyList();

    /**
     * 小程序端获取首页轮播图，公告，新闻列表
     * @param companyId
     * @return
     */
    GSysCompany getList(Long companyId);

    Long selectCompanyIdForUserId(@Param("userId") Long userId);
}