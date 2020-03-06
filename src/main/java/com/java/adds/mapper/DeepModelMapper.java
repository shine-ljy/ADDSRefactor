package com.java.adds.mapper;


import com.java.adds.entity.DeepModelEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Component
@Repository
public interface DeepModelMapper {
    /**ljy
     * 获取特定类型下的所有深度学习模型
     * @return ArrayList<DeepModelEntity>
     */
    @Select("SELECT id, model_name, model_category, config_file FROM deep_model WHERE model_category=#{categoryId}")
    @Results(id = "deepModelMap", value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "modelName", column = "model_name"),
//            @Result(property = "modelCategory", column = "model_category"),
//            @Result(property = "configFile", column = "config_file")

            @Result(property = "id", column = "id"),
            @Result(property = "modelName", column = "model_name"),
            @Result(property = "modelIntroduction", column = "model_introduction"),
            @Result(property = "modelArticleTitle", column = "model_article_title"),
            @Result(property = "modelArticleUrl", column = "model_article_url"),
            @Result(property = "modelArchitectureUrl", column = "model_architecture_url"),
            @Result(property = "modelCodeUrl", column = "model_code_url"),
            @Result(property = "modelCategory", column = "model_category"),
            @Result(property = "configFile", column = "config_file")
    })
    public ArrayList<DeepModelEntity> getAllModelOfCategory(@Param("categoryId") Integer mId);

    /**ljy
     * 根据 id 获取模型信息
     * @return DeepModelEntity
     */
    @Select("SELECT * FROM deep_model WHERE id=#{id}")
//    @Results(id = "deepModel", value = {})
    @ResultMap("deepModelMap")
    public DeepModelEntity getModelById(@Param("id") Integer id);
}
