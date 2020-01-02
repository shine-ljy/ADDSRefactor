package com.java.adds.entity;


public class DeepModelEntity {
    private Integer id;
    private String modelName;
    private String modelIntroduction;
    private String modelArticleTitle;
    private String modelArticleUrl;
    private String modelArchitectureUrl;
    private String modelCodeUrl;
    private Integer modelCategory;
    private String configFile;

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

    public String getModelIntroduction() {
        return modelIntroduction;
    }

    public void setModelIntroduction(String modelIntroduction) {
        this.modelIntroduction = modelIntroduction;
    }

    public String getModelArticleTitle() {
        return modelArticleTitle;
    }

    public void setModelArticleTitle(String modelArticleTitle) {
        this.modelArticleTitle = modelArticleTitle;
    }

    public String getModelArticleUrl() {
        return modelArticleUrl;
    }

    public void setModelArticleUrl(String modelArticleUrl) {
        this.modelArticleUrl = modelArticleUrl;
    }

    public String getModelArchitectureUrl() {
        return modelArchitectureUrl;
    }

    public void setModelArchitectureUrl(String modelArchitectureUrl) {
        this.modelArchitectureUrl = modelArchitectureUrl;
    }

    public String getModelCodeUrl() {
        return modelCodeUrl;
    }

    public void setModelCodeUrl(String modelCodeUrl) {
        this.modelCodeUrl = modelCodeUrl;
    }

    public Integer getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(Integer modelCategory) {
        this.modelCategory = modelCategory;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}
