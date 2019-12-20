package com.java.adds.entity;


public class FileEntity {
    private Long id;
    private Long user_id;
    private String dataset_path;
    private String dataset_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDataset_path() {
        return dataset_path;
    }

    public void setDataset_path(String dataset_path) {
        this.dataset_path = dataset_path;
    }

    public String getDataset_name() {
        return dataset_name;
    }

    public void setDataset_name(String dataset_name) {
        this.dataset_name = dataset_name;
    }
}
