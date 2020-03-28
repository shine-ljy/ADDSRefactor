package com.java.adds.mapper;

import com.java.adds.entity.DeepModelTaskResultEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface DeepModelTaskResultMapper {

    /**
     * QXL
     * Get Model Evaluation Task Result
     * 注：目前使用的是临时数据表 task_result_evaluation_temp
     * @param taskId task id
     * @return A DeepModelTaskResultEntity
     */
    @Select("SELECT r.*, m.model_name " +
            "FROM " +
            "task_result_evaluation_temp r LEFT JOIN deep_model m " +
            "ON r.model_id=m.id " +
            "WHERE r.task_id=#{taskId}")
    @Results(id = "taskResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "taskId", column = "task_id"),
            @Result(property = "modelId", column = "model_id"),
            @Result(property = "modelName", column = "model_name"),
            @Result(property = "map", column = "map"),
            @Result(property = "precision3", column = "p@3"),
            @Result(property = "precision5", column = "p@5"),
            @Result(property = "recall3", column = "r@3"),
            @Result(property = "recall5", column = "r@5"),
            @Result(property = "ndcg3", column = "n@3"),
            @Result(property = "ndcg5", column = "n@5")
    })
    DeepModelTaskResultEntity getModelEvaluationTaskResult(@Param("taskId") Long taskId);

    /**
     * QXL
     * Get Auto Selection Task Results
     * 注：目前使用的是临时数据表 task_result_selection_temp
     * @param taskId task id
     * @return A DeepModelTaskResultEntity ArrayList
     */
    @Select("SELECT r.*, m.model_name " +
            "FROM " +
            "task_result_selection_temp r LEFT JOIN deep_model m " +
            "ON r.model_id=m.id " +
            "WHERE r.task_id=#{taskId}")
    @ResultMap(value = "taskResultMap")
    ArrayList<DeepModelTaskResultEntity> getAutoSelectionTaskResults(@Param("taskId") Long taskId);

    /**
     * QXL
     * Get Knowledge Exploration Task Results
     * 注：目前使用的是临时数据表 task_result_exploration_temp
     * @param taskId task id
     * @return A DeepModelTaskResultEntity ArrayList
     */
    @Select("SELECT r.*, m.model_name " +
            "FROM " +
            "task_result_exploration_temp r LEFT JOIN deep_model m " +
            "ON r.model_id=m.id " +
            "WHERE r.task_id=#{taskId}")
    @ResultMap(value = "taskResultMap")
    ArrayList<DeepModelTaskResultEntity> getKnowledgeExplorationTaskResults(@Param("taskId") Long taskId);

    /**ljy
     * 插入模型运行结果
     * @return
     */
    Long insertDeepModelTaskResult(@Param("deepModelTaskResultEntity") DeepModelTaskResultEntity deepModelTaskResultEntity);
}
