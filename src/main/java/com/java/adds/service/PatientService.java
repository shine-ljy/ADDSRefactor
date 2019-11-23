package com.java.adds.service;


import com.java.adds.dao.PatientDao;
import com.java.adds.entity.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PatientService {
    @Autowired
    PatientDao patientDao;

    /**ljy
     * 管理员获取所有病人信息
     */
    public ArrayList<PatientEntity> getAllPatients()
    {
        return patientDao.getAllPatients();
    }
}
