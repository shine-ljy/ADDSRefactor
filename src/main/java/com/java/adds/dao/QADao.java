package com.java.adds.dao;

import com.java.adds.entity.ChoiceAnswerEntity;
import com.java.adds.entity.QAAnswerEntity;
import com.java.adds.entity.QAEntity;
import com.java.adds.mapper.QAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
     *根据id删除question
     * @return
     */
    public boolean deleteQuestion(Long qid)
    {
        qaMapper.deleteQuestion(qid);  //删除问题
        //qaMapper.deleteAnswersByQid(qid); //删除问题所对应的回答

        return true;
    }

    /**ljy
     *根据问题id查找答案
     * @return
     */
    public ChoiceAnswerEntity searchChoiceAnswerById(Long qid)
    {
        ChoiceAnswerEntity choiceAnswerEntity=new ChoiceAnswerEntity();
        choiceAnswerEntity.setQid(qid);
        ArrayList<Integer> answerList=qaMapper.searchChoiceAnswerById(qid);
        int yesA=0,noA=0;
        for(int i=0;i<answerList.size();i++)  //1:yes 2:no
        {
            if(answerList.get(i)==1)
                yesA++;
            else
                noA++;
        }
        choiceAnswerEntity.setYesCount(yesA);
        choiceAnswerEntity.setNoCount(noA);
        return choiceAnswerEntity;
    }

    /**ljy
     *根据问题id查找所有的详细回答
     * @return
     */
    public ArrayList<QAAnswerEntity> searchDetailAnswerById(Long qid)
    {
        return qaMapper.searchDetailAnswerById(qid);
    }

}
