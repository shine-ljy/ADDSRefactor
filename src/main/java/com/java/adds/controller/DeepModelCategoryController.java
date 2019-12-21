package com.java.adds.controller;


import com.java.adds.entity.DeepModelCategoryEntity;
import com.java.adds.entity.DeepModelEntity;
import com.java.adds.service.DeepModelCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("modelCategory")
public class DeepModelCategoryController {
    @Autowired
    DeepModelCategoryService deepModelCategoryService;

    /**ljy
     * 获取所有深度学习模型的类别
     * @return
     */
    @GetMapping("")
    public ArrayList<DeepModelCategoryEntity> getAllModelCategory()
    {
        return deepModelCategoryService.getAllModelCategory();
    }

    /**ljy
     * 获取特定类型下的所有深度学习模型
     * @return
     */
    @GetMapping("{mId}/deepModel")
    public ArrayList<DeepModelEntity> getAllModelOfCategory(@PathVariable Integer mId)
    {
        return deepModelCategoryService.getAllModelOfCategory(mId);
    }
}