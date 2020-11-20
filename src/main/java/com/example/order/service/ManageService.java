package com.example.order.service;

import java.util.List;
import java.util.Map;

import com.example.order.entity.GSysEvaluate;
import com.example.order.entity.GSysManage;
import com.example.order.entity.GSysOrder;
import com.example.order.exception.AddUserException;

public interface ManageService {

    /**
     * 公告，新闻，轮播图
     * @param objId id
     * @return
     */
    GSysManage getInfo(Long objId,String type);

    List<GSysManage> getList(String type,String searchContent);

    List<Map<String, Object>> getNewsList(String type,String searchContent);

    void addInfo(GSysManage gSysManage)  throws AddUserException;

    void updateList(GSysManage gSysManage);

    void doDelete(String objId);

    boolean reviewById(Long objId,String exaId,String remark);

    boolean releaseById(Long objId);

    /**
     * 小程序端获取首页轮播图，公告，新闻列表
     * @param type
     * @param searchContent
     * @return
     */
    List<GSysManage> getManageList(String type,String tableId,String searchContent);

    /**
     * 小程序端获取首页轮播图，公告，新闻详细信息
     * @param objId
     * @param type
     * @return
     */
    GSysManage getManageInfo(Long objId,String type);

    /**
     * 小程序首页获取新闻分区
     * @return
     */
    List<Map<String,Object>> getTableList();

    /**
     * 小程序故障上报获取故障类型列表
     * @return
     */
    List<Map<String,Object>> getFailTypeList();

    /**
     * 小程序故障上报获取单位列表
     * @return
     */
    List<Map<String,Object>> getUnitList();

    /**
     * 小程序故障上报
     * @return
     */
    void addOrder(GSysOrder gSysOrder);

    /**
     * 小程序获取业主上报订单列表
     * @return
     */
    List<Map<String,Object>> getOrderListForOwner(String openId);

    /**
     * 小程序业主订单评价
     * @return
     */
    void orderEvaluate(GSysEvaluate gSysEvaluate);

    /**
     * 小程序订单重新上报
     * @return
     */
    void reportAgain(Long orderId);

    /**
     * 小程序获取订单进度
     * @return
     */
    List<Map<String,Object>> getOrderProgress(Long orderId);

    /**
     * 小程序获取客户经理订单列表
     * @return
     */
    List<Map<String,Object>> getOrderListForManager(String managerId);

    /**
     * 小程序客户经理查看评价
     * @return
     */
    GSysEvaluate checkEvaluationDetail(Long orderId);

    /**
     * 小程序获取维修工订单列表
     * @return
     */
    List<Map<String,Object>> getOrderListForWorker(String workerId);

    /**
     * 小程序维修工转派订单获取可转派维修工列表
     * @return
     */
    List<Map<String,Object>> getWorkerArray(String workerId);

    /**
     * 小程序维修工转派订单
     * @return
     */
    void transferOrder(Long orderId,Long transferUserId,String transferDesc);

    /**
     * 小程序维修工完成订单
     * @return
     */
    void completeOrder(Long orderId);

    /**
     * 小程序维修工查看订单详情
     * @return
     */
    Map<String,Object> checkOrderDetails(Long orderId);
}
