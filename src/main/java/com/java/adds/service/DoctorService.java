package com.java.adds.service;

import com.java.adds.dao.DoctorDao;
import com.java.adds.entity.DoctorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoctorService {
    @Autowired
    DoctorDao doctorDao;

    /**ljy
     * 管理员获取所有医生信息
     */
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorDao.getAllDoctors();
    }
}
