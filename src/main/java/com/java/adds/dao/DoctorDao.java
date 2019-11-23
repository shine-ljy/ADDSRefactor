package com.java.adds.dao;

import com.java.adds.entity.DoctorEntity;
import com.java.adds.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DoctorDao {
    @Autowired
    DoctorMapper doctorMapper;

    /**ljy
     * 管理员获取所有医生信息
     */
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorMapper.getAllDoctors();
    }
}
