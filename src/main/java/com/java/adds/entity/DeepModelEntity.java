package com.java.adds.entity;


public class DeepModelEntity {
    private Integer id;
    private String modelName;
    private Integer modelCategory;
    private String configFile;

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(Integer modelCategory) {
        this.modelCategory = modelCategory;
    }
}
