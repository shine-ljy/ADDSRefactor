package com.java.adds.mapper;


import com.java.adds.entity.DeepModelTaskResultEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeepModelTaskResultMapper {
    /**ljy
     * 插入模型运行结果
     * @return
     */
    public Integer insertDeepModelTaskResult(@Param("deepModelTaskResultEntity") DeepModelTaskResultEntity deepModelTaskResultEntity);
}
