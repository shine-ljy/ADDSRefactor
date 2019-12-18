package com.java.adds.mapper;


import com.java.adds.entity.DeepModelCategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface DeepModelCategoryMapper {

    /**ljy
     * 获取所有深度学习模型的类别
     * @return
     */
    public ArrayList<DeepModelCategoryEntity> getAllModelCategory();
}
