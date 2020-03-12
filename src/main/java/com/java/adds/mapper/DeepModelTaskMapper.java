package com.java.adds.mapper;


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
    public Integer doDeepModelTask(@Param("userId") Integer doctorId, @Param("taskName")String taskName,@Param("datasetId")Integer datasetId,@Param("kgId")Integer kgId,@Param("modelId")Integer modelId,@Param("metricId")Integer metricId,@Param("status")Integer status);

    /**ljy
     * 查找是否有已经有相同的模型运行结果
     * @return
     */
    public ArrayList<DeepModelTaskEntity> getSimilarityModelTask(@Param("datasetId")Integer datasetId,@Param("kgId")Integer kgId,@Param("modelId")Integer modelId,@Param("metricId")Integer metricId);

    /**ljy
     * 更新模型运行结果
     * @return
     */
    public void updateTask(@Param("id") Integer doctorId,@Param("status") Integer status,@Param("result") Integer result,@Param("resultFilePath")String resultFilePath);

    /**
     * 医生获取所有任务
     * @author ljy
     * @return
     */
    public ArrayList<DeepModelTaskEntity> getDMTasks(@Param("userId") Integer doctorId);
}
