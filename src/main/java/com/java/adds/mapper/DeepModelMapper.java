package com.java.adds.mapper;


import com.java.adds.entity.DeepModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface DeepModelMapper {
    /**ljy
     * 获取特定类型下的所有深度学习模型
     * @return
     */
    public ArrayList<DeepModelEntity> getAllModelOfCategory(@Param("categoryId") Integer mId);

    /**ljy
     * 根据id获取模型信息
     * @return
     */
    public DeepModelEntity getModelById(@Param("id") Integer id);
}
