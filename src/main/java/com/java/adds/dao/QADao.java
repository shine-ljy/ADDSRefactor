package com.java.adds.dao;

import com.java.adds.entity.QAEntity;
import com.java.adds.mapper.QAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Component
public class QADao {
    @Autowired
    QAMapper qaMapper;

    /**ljy
     *新增question
     * @return
     */
    public boolean addQuestion(QAEntity qaEntity)
    {
        qaMapper.addQuestion(qaEntity);
        return true;
    }

    /**ljy
     *根据问题id查找答案
     * @return
     */
    public Integer searchAnswerById(Long qid)
    {
        ArrayList<Integer> answerList=qaMapper.searchAnswerById(qid);
        if(answerList==null)
            return 401;  //该问题还没有人回答
        int yesA=0,noA=0;
        for(int i=0;i<answerList.size();i++)  //1:yes 2:no
        {
            if(answerList.get(i)==1)
                yesA++;
            else
                noA++;
        }
        if(yesA>=noA)
            return 1;
        else
            return 2;
    }

    /**ljy
     *查找自己提交的所有问题
     * @return
     */
    public ArrayList<QAEntity> searchMyQuestion(Long uid)
    {
        return qaMapper.searchMyQuestion(uid);
    }

    /**ljy
     *获取所有问题
     * @return
     */
    public ArrayList<QAEntity> getAllQuestions()
    {
        return qaMapper.getAllQuestions();
    }
}
