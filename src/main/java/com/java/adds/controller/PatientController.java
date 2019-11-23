package com.java.adds.controller;

import com.java.adds.entity.PatientEntity;
import com.java.adds.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    /**ljy
     * 管理员获取所有病人信息
     */
    @GetMapping("")
    public ArrayList<PatientEntity> getAllPatients()
    {
        return patientService.getAllPatients();
    }
}
