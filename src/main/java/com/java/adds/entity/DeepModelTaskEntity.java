package com.java.adds.entity;


public class DeepModelTaskEntity {
    private Integer id;
    private String taskName;
    private Integer datasetId;
    private Integer kgId;
    private Integer modelType;  //模型类型
    private Integer modelId;
    private Integer metricId;
    private Integer status;  //0:正在运行模型，1：模型运行结束
    private Integer userId;
    private Integer resultId;
    private String resultFilePath; //结果文件存放路径

    public String getResultFilePath() {
        return resultFilePath;
    }

    public void setResultFilePath(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Integer datasetId) {
        this.datasetId = datasetId;
    }

    public Integer getKgId() {
        return kgId;
    }

    public void setKgId(Integer kgId) {
        this.kgId = kgId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getMetricId() {
        return metricId;
    }

    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer result) {
        this.resultId = result;
    }
}
