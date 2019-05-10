package com.swain.core.common.base;


import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.*;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BaseManagerImpl<M extends BaseDao<T>, T extends BaseModel> implements BaseManager<T> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected M baseDao;

    public BaseManagerImpl() {
    }

    @Override
    public Integer insert(T entity) {
        return this.baseDao.insert(entity);
    }
    @Override
    public Integer insertAllColumn(T entity) {
        return this.baseDao.insertAllColumn(entity);
    }
    @Override
    public Integer insertBatch(List<T> entityList) {
        return this.insertBatch(entityList, 30);
    }
    @Override
    public Integer insertBatch(List<T> entityList, int batchSize) {
        int result = 0;
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        } else {
            try {
                SqlSession batchSqlSession = this.sqlSessionBatch();
                Throwable var5 = null;

                try {
                    int size = entityList.size();
                    String sqlStatement = this.sqlStatement(SqlMethod.INSERT_ONE);

                    for(int i = 0; i < size; ++i) {
                        int r = batchSqlSession.insert(sqlStatement, entityList.get(i));
                        result += r;
                        if (i >= 1 && i % batchSize == 0) {
                            batchSqlSession.flushStatements();
                        }
                    }

                    batchSqlSession.flushStatements();
                    return result;
                } catch (Throwable var18) {
                    var5 = var18;
                    throw var18;
                } finally {
                    if (batchSqlSession != null) {
                        if (var5 != null) {
                            try {
                                batchSqlSession.close();
                            } catch (Throwable var17) {
                                var5.addSuppressed(var17);
                            }
                        } else {
                            batchSqlSession.close();
                        }
                    }

                }
            } catch (Throwable var20) {
                throw new MybatisPlusException("Error: Cannot execute insertBatch Method. Cause", var20);
            }
        }
    }
    @Override
    public Integer insertOrUpdate(T entity) {
        int result = 0;
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null == tableInfo || !StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }

            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            if (StringUtils.checkValNull(idVal)) {
                result = this.insert(entity);
            } else {
                result = this.updateById(entity);
                if (result == 0) {
                    result = this.insert(entity);
                }
            }
        }

        return result;
    }
    @Override
    public Integer insertOrUpdateAllColumn(T entity) {
        int result = 0;
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null == tableInfo || !StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }

            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            if (StringUtils.checkValNull(idVal)) {
                result = this.insertAllColumn(entity);
            } else {
                result = this.updateAllColumnById(entity);
                if (result == 0) {
                    result = this.insertAllColumn(entity);
                }
            }
        }

        return result;
    }
    @Override
    public Integer insertOrUpdateBatch(List<T> entityList) {
        return this.insertOrUpdateBatch(entityList, 30);
    }
    @Override
    public Integer insertOrUpdateBatch(List<T> entityList, int batchSize) {
        return this.insertOrUpdateBatch(entityList, batchSize, true);
    }
    @Override
    public Integer insertOrUpdateAllColumnBatch(List<T> entityList) {
        return this.insertOrUpdateBatch(entityList, 30, false);
    }
    @Override
    public Integer insertOrUpdateAllColumnBatch(List<T> entityList, int batchSize) {
        return this.insertOrUpdateBatch(entityList, batchSize, false);
    }

    private Integer insertOrUpdateBatch(List<T> entityList, int batchSize, boolean selective) {
        int result = 0;
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        } else {
            try {
                SqlSession batchSqlSession = this.sqlSessionBatch();
                Throwable var6 = null;

                try {
                    int size = entityList.size();

                    for(int i = 0; i < size; ++i) {
                        int r;
                        if (selective) {
                            r = this.insertOrUpdate((T) entityList.get(i));
                            result += r;
                        } else {
                            r = this.insertOrUpdateAllColumn((T)entityList.get(i));
                            result += r;
                        }

                        if (i >= 1 && i % batchSize == 0) {
                            batchSqlSession.flushStatements();
                        }
                    }

                    batchSqlSession.flushStatements();
                    return result;
                } catch (Throwable var18) {
                    var6 = var18;
                    throw var18;
                } finally {
                    if (batchSqlSession != null) {
                        if (var6 != null) {
                            try {
                                batchSqlSession.close();
                            } catch (Throwable var17) {
                                var6.addSuppressed(var17);
                            }
                        } else {
                            batchSqlSession.close();
                        }
                    }

                }
            } catch (Throwable var20) {
                throw new MybatisPlusException("Error: Cannot execute insertOrUpdateBatch Method. Cause", var20);
            }
        }
    }
    @Override
    public Integer deleteById(Serializable id) {
        return this.baseDao.deleteById(id);
    }
    @Override
    public Integer deleteByMap(Map<String, Object> columnMap) {
        if (MapUtils.isEmpty(columnMap)) {
            throw new MybatisPlusException("deleteByMap columnMap is empty.");
        } else {
            return this.baseDao.deleteByMap(columnMap);
        }
    }
    @Override
    public Integer delete(Wrapper<T> wrapper) {
        return this.baseDao.delete(wrapper);
    }
    @Override
    public Integer deleteBatchIds(List<? extends Serializable> idList) {
        return this.baseDao.deleteBatchIds(idList);
    }
    @Override
    public Integer updateById(T entity) {
        return this.baseDao.updateById(entity);
    }
    @Override
    public Integer updateAllColumnById(T entity) {
        return this.baseDao.updateAllColumnById(entity);
    }
    @Override
    public Integer update(T entity, Wrapper<T> wrapper) {
        return this.baseDao.update(entity, wrapper);
    }
    @Override
    public Integer updateBatchById(List<T> entityList) {
        return this.updateBatchById(entityList, 30);
    }
    @Override
    public Integer updateBatchById(List<T> entityList, int batchSize) {
        return this.updateBatchById(entityList, batchSize, true);
    }
    @Override
    public Integer updateAllColumnBatchById(List<T> entityList) {
        return this.updateAllColumnBatchById(entityList, 30);
    }
    @Override
    public Integer updateAllColumnBatchById(List<T> entityList, int batchSize) {
        return this.updateBatchById(entityList, batchSize, false);
    }

    private Integer updateBatchById(List<T> entityList, int batchSize, boolean selective) {
        int result = 0;
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        } else {
            try {
                SqlSession batchSqlSession = this.sqlSessionBatch();
                Throwable var6 = null;

                try {
                    int size = entityList.size();
                    SqlMethod sqlMethod = selective ? SqlMethod.UPDATE_BY_ID : SqlMethod.UPDATE_ALL_COLUMN_BY_ID;
                    String sqlStatement = this.sqlStatement(sqlMethod);

                    for(int i = 0; i < size; ++i) {
                        ParamMap<T> param = new ParamMap();
                        param.put("et", entityList.get(i));
                        int r = batchSqlSession.update(sqlStatement, param);
                        result += r;
                        if (i >= 1 && i % batchSize == 0) {
                            batchSqlSession.flushStatements();
                        }
                    }

                    batchSqlSession.flushStatements();
                    return result;
                } catch (Throwable var21) {
                    var6 = var21;
                    throw var21;
                } finally {
                    if (batchSqlSession != null) {
                        if (var6 != null) {
                            try {
                                batchSqlSession.close();
                            } catch (Throwable var20) {
                                var6.addSuppressed(var20);
                            }
                        } else {
                            batchSqlSession.close();
                        }
                    }

                }
            } catch (Throwable var23) {
                throw new MybatisPlusException("Error: Cannot execute updateBatchById Method. Cause", var23);
            }
        }
    }
    @Override
    public T selectById(Serializable id) {
        return (T)this.baseDao.selectById(id);
    }
    @Override
    public List<T> selectBatchIds(List<? extends Serializable> idList) {
        return this.baseDao.selectBatchIds(idList);
    }
    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        return this.baseDao.selectByMap(columnMap);
    }
    @Override
    public T selectOne(Wrapper<T> wrapper) {
        return (T)SqlHelper.getObject(this.baseDao.selectList(wrapper));
    }
    @Override
    public T selectOne(T entity) {
        return (T)SqlHelper.getObject(this.baseDao.selectList(new EntityWrapper(entity)));
    }
    @Override
    public Map<String, Object> selectMap(Wrapper<T> wrapper) {
        return (Map)SqlHelper.getObject(this.baseDao.selectMaps(wrapper));
    }
    @Override
    public Object selectObj(Wrapper<T> wrapper) {
        return SqlHelper.getObject(this.baseDao.selectObjs(wrapper));
    }
    @Override
    public int selectCount(Wrapper<T> wrapper) {
        return SqlHelper.retCount(this.baseDao.selectCount(wrapper));
    }
    @Override
    public List<T> selectList(Wrapper<T> wrapper) {
        return this.baseDao.selectList(wrapper);
    }
    @Override
    public Page<T> selectPage(Page<T> page) {
        return this.selectPage(page, Condition.EMPTY);
    }
    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> wrapper) {
        return this.baseDao.selectMaps(wrapper);
    }

    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(this.currentModelClass());
    }

    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(this.currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }
    @Override
    public List<Object> selectObjs(Wrapper<T> wrapper) {
        return this.baseDao.selectObjs(wrapper);
    }
    @Override
    public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<T> wrapper) {
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(this.baseDao.selectMapsPage(page, wrapper));
        return page;
    }
    @Override
    public Page<T> selectPage(Page<T> page, Wrapper<T> wrapper) {
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(this.baseDao.selectPage(page, wrapper));
        return page;
    }
    @Override
    public List<Long> selectIdPage(Map<String, Object> params) {
        return this.baseDao.selectIdPage(params);
    }
    @Override
    public List<Long> selectIdPage(RowBounds rowBounds, Map<String, Object> params) {
        return this.baseDao.selectIdPage(rowBounds, params);
    }

    protected Class<T> currentModelClass() {
        return ReflectionKit.getSuperClassGenricType(this.getClass(), 1);
    }
}
