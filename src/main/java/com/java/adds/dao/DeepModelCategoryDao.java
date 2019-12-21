package com.java.adds.dao;

import com.java.adds.entity.DeepModelCategoryEntity;
import com.java.adds.entity.DeepModelEntity;
import com.java.adds.mapper.DeepModelCategoryMapper;
import com.java.adds.mapper.DeepModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DeepModelCategoryDao {
    @Autowired
    DeepModelCategoryMapper deepModelCategoryMapper;

    @Autowired
    DeepModelMapper deepModelMapper;

    /**ljy
     * 获取所有深度学习模型的类别
     * @return
     */
    public ArrayList<DeepModelCategoryEntity> getAllModelCategory()
    {
        return deepModelCategoryMapper.getAllModelCategory();
    }

    /**ljy
     * 获取特定类型下的所有深度学习模型
     * @return
     */
    public ArrayList<DeepModelEntity> getAllModelOfCategory(Integer mId)
    {
        return deepModelMapper.getAllModelOfCategory(mId);
    }
}
