package com.java.adds.service;


import com.java.adds.dao.DeepModelCategoryDao;
import com.java.adds.dao.DepartmentDao;
import com.java.adds.entity.DeepModelCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
}
