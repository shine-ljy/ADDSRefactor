package com.java.adds.service;

import com.java.adds.dao.QADao;
import com.java.adds.entity.QAEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QAService {
    @Autowired
    QADao qaDao;
    /**ljy
     *新增question
     * @return
     */
    public boolean addQuestion(QAEntity qaEntity)
    {
        return qaDao.addQuestion(qaEntity);
    }

    /**ljy
     *根据问题id查找答案
     * @return
     */
    public Integer searchAnswerById(Long qid)
    {
        return qaDao.searchAnswerById(qid);
    }

    /**ljy
     *查找自己提交的所有问题
     * @return
     */
    public ArrayList<QAEntity> searchMyQuestion(Long uid)
    {
        return qaDao.searchMyQuestion(uid);
    }

    /**ljy
     *获取所有问题
     * @return
     */
    public ArrayList<QAEntity> getAllQuestions()
    {
        return qaDao.getAllQuestions();
    }
}
