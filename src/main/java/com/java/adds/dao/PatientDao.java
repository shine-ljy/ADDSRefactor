package com.java.adds.dao;

import com.java.adds.entity.PatientEntity;
import com.java.adds.entity.QAEntity;
import com.java.adds.mapper.PatientMapper;
import com.java.adds.mapper.QAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PatientDao {
    @Autowired
    PatientMapper patientMapper;

    @Autowired
    QAMapper qaMapper;
    /**ljy
     * 管理员获取所有病人信息
     */
    public ArrayList<PatientEntity> getAllPatients()
    {
        return patientMapper.getAllPatients();
    }

    /**ljy
     *病人查找自己提交的所有问题
     * @return
     */
    public ArrayList<QAEntity> searchMyQuestion(Long uid)
    {
        return qaMapper.searchMyQuestion(uid);
    }
}
