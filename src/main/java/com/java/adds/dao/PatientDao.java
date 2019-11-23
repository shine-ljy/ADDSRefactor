package com.java.adds.dao;

import com.java.adds.entity.PatientEntity;
import com.java.adds.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PatientDao {
    @Autowired
    PatientMapper patientMapper;
    /**ljy
     * 管理员获取所有病人信息
     */
    public ArrayList<PatientEntity> getAllPatients()
    {
        return patientMapper.getAllPatients();
    }
}
