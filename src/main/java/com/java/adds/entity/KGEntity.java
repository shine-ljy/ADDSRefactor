package com.java.adds.entity;


public class KGEntity {
    private Long id;
    private Long user_id;
    private String kg_path;
    private String kg_name;

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

    public String getKg_path() {
        return kg_path;
    }

    public void setKg_path(String kg_path) {
        this.kg_path = kg_path;
    }

    public String getKg_name() {
        return kg_name;
    }

    public void setKg_name(String kg_name) {
        this.kg_name = kg_name;
    }
}
