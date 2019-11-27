package com.java.adds.controller;


import com.java.adds.entity.ChoiceAnswerEntity;
import com.java.adds.entity.QAAnswerEntity;
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
    @PutMapping("")
    public boolean addQuestion(@RequestBody QAEntity qaEntity)
    {
        return qaService.addQuestion(qaEntity);
    }

    /**ljy
     *根据id删除question
     * @return
     */
    @DeleteMapping("{qid}")
    public boolean deleteQuestion(@PathVariable Long qid)
    {
        return qaService.deleteQuestion(qid);
    }

    /**ljy
     *根据问题id查找选择题答案
     * @return
     */
    @GetMapping("{qid}/simpleAnswer")
    public ChoiceAnswerEntity searchChoiceAnswerById(@PathVariable Long qid)
    {
        return qaService.searchChoiceAnswerById(qid);
    }

    /**ljy
     *根据问题id查找所有的详细回答
     * @return
     */
    @GetMapping("{qid}/detailAnswer")
    public ArrayList<QAAnswerEntity> searchDetailAnswerById(@PathVariable Long qid)
    {
        return qaService.searchDetailAnswerById(qid);
    }
}
