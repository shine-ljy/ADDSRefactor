package com.java.adds.mapper;

import com.java.adds.entity.DeepModelTaskEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Deep Learning Task Mapper
 * @author QXL
 */
@Mapper
@Repository
public interface TaskMapper {

    /**
     * Get all Model Evaluation Tasks
     * @param userId user's id
     * @return A DeepModelTaskEntity ArrayList
     */
    @Select("SELECT * FROM model_evaluation_task WHERE user_id=#{userId}")
    @Results(id = "modelEvaluationTaskMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "taskName", column = "task_name"),
            @Result(property = "datasetId", column = "dataset_id"),
            @Result(property = "queryLength", column = "query_length"),
            @Result(property = "documentLength", column = "document_length"),
            @Result(property = "modelId", column = "model_id"),
            @Result(property = "metricId", column = "metric_id"),
            @Result(property = "status", column = "status"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "resultId", column = "result_id"),
            @Result(property = "resultFilePath", column = "result_file_path")
    })
    ArrayList<DeepModelTaskEntity> getModelEvaluationTasks(@Param("userId") Long userId);

    /**
     * Add A Model Evaluation Task
     * @param task Model Evaluation Task Entity
     */
    @Insert("INSERT INTO model_evaluation_task(task_name, dataset_id, query_length, document_length, model_id, metric_id, user_id) " +
            "VALUES (#{task.taskName}, #{task.datasetId}, #{task.queryLength}, #{task.documentLength}, #{task.modelId}, #{task.metricId}, #{task.userId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "task.id")
    void addModelEvaluationTask(@Param("task") DeepModelTaskEntity task);

    /**
     * Get all Auto Selection Tasks
     * @param userId user's id
     * @return A DeepModelTaskEntity ArrayList
     */
    @Select("SELECT * FROM auto_selection_task WHERE user_id=#{userId}")
    @Results(id = "autoSelectionTaskMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "taskName", column = "name"),
            @Result(property = "datasetId", column = "dataset_id"),
            @Result(property = "metricId", column = "metric_id"),
            @Result(property = "queryLength", column = "query_length"),
            @Result(property = "documentLength", column = "document_length"),
            @Result(property = "status", column = "status"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "resultId", column = "result_id"),
            @Result(property = "resultFilePath", column = "result_file")
    })
    ArrayList<DeepModelTaskEntity> getAutoSelectionTasks(@Param("userId") Long userId);

    /**
     * Add A Auto Selection Task
     * @param task Auto Selection Task Entity
     */
    @Insert("INSERT INTO auto_selection_task(name, dataset_id, query_length, document_length, metric_id, user_id) " +
            "VALUES (#{task.taskName}, #{task.datasetId}, #{task.queryLength}, #{task.documentLength}, #{task.metricId}, #{task.userId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "task.id")
    void addAutoSelectionTask(@Param("task") DeepModelTaskEntity task);

    /**
     * Get all Knowledge-Embedding Exploration Tasks
     * @param userId user's id
     * @return A DeepModelTaskEntity ArrayList
     */
    @Select("SELECT * FROM knowledge_embedding_exploration_task WHERE user_id=#{userId}")
    @Results(id = "knowledgeEmbeddingExplorationTaskMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "taskName", column = "name"),
            @Result(property = "datasetId", column = "dataset_id"),
            @Result(property = "metricId", column = "metric_id"),
            @Result(property = "queryLength", column = "query_length"),
            @Result(property = "documentLength", column = "document_length"),
            @Result(property = "status", column = "status"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "resultId", column = "result_id"),
            @Result(property = "resultFilePath", column = "result_file")
    })
    ArrayList<DeepModelTaskEntity> getKnowledgeEmbeddingExplorationTasks(@Param("userId") Long userId);

    /**
     * Add A Knowledge-Embedding Exploration Task
     * @param task Knowledge-Embedding Exploration Task Entity
     */
    @Insert("INSERT INTO knowledge_embedding_exploration_task(name, dataset_id, query_length, document_length, metric_id, user_id) " +
            "VALUES (#{task.taskName}, #{task.datasetId}, #{task.queryLength}, #{task.documentLength}, #{task.metricId}, #{task.userId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "task.id")
    void addKnowledgeEmbeddingExplorationTask(@Param("task") DeepModelTaskEntity task);
}
