package com.example.order.utils;

import com.example.order.result.PageResult;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName PageUtils
 * @Description PageHelper分页工具类
 * @Author xionggy
 * @Date 2020/9/3
 * @Version 1.0
 */
public class PageUtils {

    /**
     * 将分页信息封装到统一的接口
     */
    public static PageResult getPageResult( PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }

}
