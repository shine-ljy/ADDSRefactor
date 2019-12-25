package com.java.adds.mapper;


import com.java.adds.entity.DeepModelEntity;
import com.java.adds.entity.DeepModelTaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface DeepModelTaskMapper {
    /**ljy
     * 医生运行一个深度学习模型
     * @return
     */
    public void doDeepModelTask(@Param("userId") Integer doctorId, @Param("datasetId")Integer datasetId,@Param("kgId")Integer kgId,@Param("modelId")Integer modelId,@Param("metricId")Integer metricId,@Param("status")Integer status);

}
