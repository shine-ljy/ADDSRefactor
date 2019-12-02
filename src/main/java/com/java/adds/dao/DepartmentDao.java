package com.java.adds.dao;

import com.java.adds.entity.DoctorEntity;
import com.java.adds.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DepartmentDao {
    @Autowired
    DoctorMapper doctorMapper;

    /**ljy
     * 根据科室id获取科室下所有医生
     */
    public ArrayList<DoctorEntity> getDoctorsByDepartment(Long did)
    {
        return doctorMapper.getDoctorsByDepartment(did);
    }
}
