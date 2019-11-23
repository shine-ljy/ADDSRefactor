package com.java.adds.controller;


import com.java.adds.entity.QAEntity;
import com.java.adds.service.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("QA")
public class QAController {
    @Autowired
    QAService qaService;


    /**ljy
     *新增question
     * @return
     */
    @PutMapping("question")
    public boolean addQuestion(@RequestBody QAEntity qaEntity)
    {
        return qaService.addQuestion(qaEntity);
    }

    /**ljy
     *根据问题id查找答案
     * @return
     */
    @GetMapping("{qid}/answer")
    public Integer searchAnswerById(@PathVariable Long qid)
    {
        return qaService.searchAnswerById(qid);
    }

    /**ljy
     *查找自己提交的所有问题
     * @return
     */
    @GetMapping("{uid}/question")
    public ArrayList<QAEntity> searchMyQuestion(@PathVariable Long uid)
    {
        return qaService.searchMyQuestion(uid);
    }

    /**ljy
     *获取所有问题
     * @return
     */
    @GetMapping("question")
    public ArrayList<QAEntity> getAllQuestions()
    {
        return qaService.getAllQuestions();
    }
}
