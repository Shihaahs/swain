package com.swain.core.common.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseDao<T extends BaseModel> extends BaseMapper<T> {
    List<Long> selectIdPage(@Param("cm") Map<String, Object> var1);

    List<Long> selectIdPage(RowBounds var1, @Param("cm") Map<String, Object> var2);
}
