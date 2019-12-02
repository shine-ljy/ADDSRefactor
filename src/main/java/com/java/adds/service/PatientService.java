package com.java.adds.service;


import com.java.adds.dao.PatientDao;
import com.java.adds.entity.PatientEntity;
import com.java.adds.entity.QAEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PatientService {
    @Autowired
    PatientDao patientDao;

    /**ljy
     *新增question
     * @return
     */
    public boolean addQuestion(QAEntity qaEntity)
    {
        return patientDao.addQuestion(qaEntity);
    }

    /**ljy
     * 管理员获取所有病人信息
     */
    public ArrayList<PatientEntity> getAllPatients()
    {
        return patientDao.getAllPatients();
    }

    /**ljy
     *病人查找自己提交的所有问题
     * @return
     */
    public ArrayList<QAEntity> searchMyQuestion(Long uid)
    {
        return patientDao.searchMyQuestion(uid);
    }
}
