package com.java.adds.dao;

import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.controller.vo.SetPage;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.QAEntity;
import com.java.adds.mapper.DoctorMapper;
import com.java.adds.mapper.QuestionDetailAnswerMapper;
import com.java.adds.mapper.QuestionMapper;
import com.java.adds.mapper.QuestionResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DoctorDao {
    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionResultMapper questionResultMapper;

    @Autowired
    QuestionDetailAnswerMapper questionDetailAnswerMapper;

    /**ljy
     * 管理员获取所有医生信息
     */
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorMapper.getAllDoctors();
    }

    /**ljy
     *获取所有还没回答的选择题
     * @return
     */
    public ArrayList<QAEntity> getChoiceQuestionsNotAnswered(Long uid, SetPage setPage)
    {
        ArrayList<QAEntity> allQuestions=questionMapper.getChoiceQuestionsNotAnswered((setPage.getStart()-1)*setPage.getLimit(),setPage.getLimit(),uid);
//        ArrayList<QAEntity> questionsAnswered=questionMapper.getQuestionAnswered(uid);
////        for(int i=0;i<allQuestions.size();i++) {
////            allQuestions.get(i).setAnswered(2);  //初始全部设为未回答
////            for (int j = 0; j < questionsAnswered.size(); j++) {
////                if (allQuestions.get(i).getQid() == questionsAnswered.get(j).getQid()) {
////                    //allQuestions.remove(i);
////                    allQuestions.get(i).setAnswered(1);  //已回答的修改未已回答
////                    break;
////                }
////            }
////        }
        return allQuestions;
    }


    /**ljy
     *医生获取所有已经回答的问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsAnswered(SetPage setPage,Long uid)
    {
        return questionMapper.getQuestionAnswered((setPage.getStart()-1)*setPage.getLimit(),setPage.getLimit(),uid);
    }

    /**ljy
     *获取某一个科室下的未回答问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsInHosDepartment(Long uid,Long hdId)
    {
        ArrayList<QAEntity> questions=questionMapper.getQuestionsInHosDepartment(hdId);
        ArrayList<QAEntity> questionsAnswered=questionMapper.getAllQuestionAnswered(uid);
        for(int i=0;i<questions.size();i++) {
            for (int j = 0; j < questionsAnswered.size(); j++) {
                if (questions.get(i).getQid() == questionsAnswered.get(j).getQid()) {
                    questions.remove(i);
                    i--;
                    break;
                }
            }
        }
        return questions;
    }

    /**ljy
     * 医生回答某个问题
     * @return
     */
    public boolean insertAnswer(Long uid, Long qid, QuestionAnswerVO questionAnswerVO)
    {
        if(questionAnswerVO.getType()==1)  //选择题
        {
            int answer=0;
            if(questionAnswerVO.getAnswer().equals("yes"))  //1:yes,2:no
                answer=1;
            else
                answer=2;
            questionResultMapper.insertChoiceAnswer(uid,qid,answer, questionAnswerVO.getRemark());
        }
        else
            questionDetailAnswerMapper.insertDetailAnswer(uid,qid, questionAnswerVO.getAnswer(), questionAnswerVO.getRemark());

        return true;
    }


    /**ljy
     * 医生获取所有还未回答的详细解答题
     * @return
     */
    public ArrayList<QAEntity> getDetailQuestionsNotAnswered(SetPage setPage, Long doctorId)
    {
        return questionMapper.getDetailQuestionsNotAnswered((setPage.getStart()-1)*setPage.getLimit(),setPage.getLimit(),doctorId);
    }
}
