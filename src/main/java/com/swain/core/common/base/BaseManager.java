package com.swain.core.common.base;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseManager<T> {
    Integer insert(T var1);

    Integer insertAllColumn(T var1);

    Integer insertBatch(List<T> var1);

    Integer insertBatch(List<T> var1, int var2);

    Integer insertOrUpdateBatch(List<T> var1);

    Integer insertOrUpdateBatch(List<T> var1, int var2);

    Integer insertOrUpdateAllColumnBatch(List<T> var1);

    Integer insertOrUpdateAllColumnBatch(List<T> var1, int var2);

    Integer deleteById(Serializable var1);

    Integer deleteByMap(Map<String, Object> var1);

    Integer delete(Wrapper<T> var1);

    Integer deleteBatchIds(List<? extends Serializable> var1);

    Integer updateById(T var1);

    Integer updateAllColumnById(T var1);

    Integer update(T var1, Wrapper<T> var2);

    Integer updateBatchById(List<T> var1);

    Integer updateBatchById(List<T> var1, int var2);

    Integer updateAllColumnBatchById(List<T> var1);

    Integer updateAllColumnBatchById(List<T> var1, int var2);

    Integer insertOrUpdate(T var1);

    Integer insertOrUpdateAllColumn(T var1);

    T selectById(Serializable var1);

    List<T> selectBatchIds(List<? extends Serializable> var1);

    List<T> selectByMap(Map<String, Object> var1);

    T selectOne(Wrapper<T> var1);

    T selectOne(T var1);

    Map<String, Object> selectMap(Wrapper<T> var1);

    Object selectObj(Wrapper<T> var1);

    int selectCount(Wrapper<T> var1);

    List<T> selectList(Wrapper<T> var1);

    Page<T> selectPage(Page<T> var1);

    List<Map<String, Object>> selectMaps(Wrapper<T> var1);

    List<Object> selectObjs(Wrapper<T> var1);

    Page<Map<String, Object>> selectMapsPage(Page var1, Wrapper<T> var2);

    Page<T> selectPage(Page<T> var1, Wrapper<T> var2);

    List<Long> selectIdPage(Map<String, Object> var1);

    List<Long> selectIdPage(RowBounds var1, Map<String, Object> var2);
}
