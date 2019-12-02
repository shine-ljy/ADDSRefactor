package com.java.adds.controller;

import com.java.adds.entity.PatientEntity;
import com.java.adds.entity.QAEntity;
import com.java.adds.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**ljy
     *病人查找自己提交的所有问题
     * @return
     */
    @GetMapping("{patientId}/question")
    public ArrayList<QAEntity> searchMyQuestion(@PathVariable Long patientId)
    {
        return patientService.searchMyQuestion(patientId);
    }

    /**ljy
     *新增question
     * @return
     */
    @PutMapping("")
    public boolean addQuestion(@RequestBody QAEntity qaEntity)
    {
        return patientService.addQuestion(qaEntity);
    }
}
