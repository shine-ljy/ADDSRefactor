package com.java.adds.service;


import com.java.adds.dao.DeepModelCategoryDao;
import com.java.adds.entity.DeepModelCategoryEntity;
import com.java.adds.entity.DeepModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeepModelCategoryService {
    @Autowired
    DeepModelCategoryDao deepModelCategoryDao;

    /**ljy
     * 获取所有深度学习模型的类别
     * @return
     */
    public ArrayList<DeepModelCategoryEntity> getAllModelCategory()
    {
        return deepModelCategoryDao.getAllModelCategory();
    }

    /**ljy
     * 获取特定类型下的所有深度学习模型
     * @return
     */
    public ArrayList<DeepModelEntity> getAllModelOfCategory(Integer mId)
    {
        return deepModelCategoryDao.getAllModelOfCategory(mId);
    }

    /**
     * 根据 id 获取模型信息
     * @param mId Deep Model Id: Integer
     * @return DeepModelEntity
     * @author Liam
     */
    public DeepModelEntity getModelById(Integer mId) {
        return deepModelCategoryDao.getModelById(mId);
    }
}
