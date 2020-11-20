package com.example.order.mapper;

import com.example.order.entity.GSysManage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface GSysManageMapper {

    GSysManage getInfo(Long objId,String type);

    List<GSysManage> getList(String type,String searchContent);

    List<Map<String,Object>> getNewsList(String type,String searchContent);

    int deleteByPrimaryKey(Long objId);

    int insert(GSysManage record);

    int insertSelective(GSysManage gSysManage);

    int updateByPrimaryKey(GSysManage record);

    void updateByPrimaryKeySelective(GSysManage gSysManage);

    void updateIsdelByOId(String objId);

    GSysManage selectByPrimaryKey(Long objId);

    /**
     * 小程序端获取首页轮播图，公告，新闻列表
     * @param type
     * @param searchContent
     * @return
     */
    List<GSysManage> getManageList(@Param("type") String type,@Param("tableId") String tableId, @Param("searchContent") String searchContent);

    /**
     * 小程序端获取首页轮播图，公告，新闻详细信息
     * @param objId
     * @param type
     * @return
     */
    GSysManage getManageByTypeAndObjId(@Param("objId")Long objId, @Param("type") String type);

    /**
     * 根据selectCode获取数据
     * @return
     */
    List<Map<String,Object>> getTableList(@Param("selectCode")String selectCode);

    /**
     * 小程序故障上报获取单位列表
     * @return
     */
    List<Map<String,Object>> getUnitList();
}