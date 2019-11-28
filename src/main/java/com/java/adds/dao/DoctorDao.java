package com.java.adds.dao;

import com.java.adds.controller.vo.SetPage;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.QAEntity;
import com.java.adds.mapper.DoctorMapper;
import com.java.adds.mapper.QAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DoctorDao {
    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    QAMapper qaMapper;

    /**ljy
     * 管理员获取所有医生信息
     */
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorMapper.getAllDoctors();
    }

    /**ljy
     *获取所有问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsNotAnswered(Long uid, SetPage setPage)
    {
        ArrayList<QAEntity> allQuestions=qaMapper.getAllQuestions((setPage.getStart()-1)*setPage.getLimit(),setPage.getLimit());
        ArrayList<QAEntity> questionsAnswered=qaMapper.getQuestionAnswered(uid);
        for(int i=0;i<allQuestions.size();i++) {
            allQuestions.get(i).setAnswered(2);  //初始全部设为未回答
            for (int j = 0; j < questionsAnswered.size(); j++) {
                if (allQuestions.get(i).getQid() == questionsAnswered.get(j).getQid()) {
                    //allQuestions.remove(i);
                    allQuestions.get(i).setAnswered(1);  //已回答的修改未已回答
                    break;
                }
            }
        }
        return allQuestions;
    }


    /**ljy
     *医生获取所有已经回答的问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsAnswered(Long uid)
    {
        return qaMapper.getQuestionAnswered(uid);
    }

    /**ljy
     *获取某一个科室下的未回答问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsInHosDepartment(Long uid,Long hdId)
    {
        ArrayList<QAEntity> questions=qaMapper.getQuestionsInHosDepartment(hdId);
        ArrayList<QAEntity> questionsAnswered=qaMapper.getQuestionAnswered(uid);
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
}
