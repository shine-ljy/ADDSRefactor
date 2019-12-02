package com.java.adds.dao;

import com.java.adds.entity.ChoiceAnswerEntity;
import com.java.adds.entity.QAAnswerEntity;
import com.java.adds.mapper.QuestionDetailAnswerMapper;
import com.java.adds.mapper.QuestionMapper;
import com.java.adds.mapper.QuestionResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QADao {
    @Autowired
    QuestionDetailAnswerMapper questionDetailAnswerMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionResultMapper questionResultMapper;


    /**ljy
     *根据id删除question
     * @return
     */
    public boolean deleteQuestion(Long qid)
    {
        questionMapper.deleteQuestion(qid);  //删除问题
        //questionResultMapper.deleteAnswersByQid(qid); //删除问题所对应的回答

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
        choiceAnswerEntity.setContent(questionMapper.getQuestionById(qid).getContent());
        ArrayList<Integer> answerList=questionResultMapper.searchChoiceAnswerById(qid);
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
        return questionDetailAnswerMapper.searchDetailAnswerById(qid);
    }

}
