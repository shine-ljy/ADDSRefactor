package com.java.adds.dao;

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
    public ArrayList<QAEntity> getQuestionsNotAnswered(Long uid)
    {
        ArrayList<QAEntity> allQuestions=qaMapper.getAllQuestions();
        ArrayList<QAEntity> questionsAnswered=qaMapper.getQuestionAnswered(uid);
        for(int i=0;i<allQuestions.size();i++) {
            for (int j = 0; j < questionsAnswered.size(); j++) {
                if (allQuestions.get(i).getQid() == questionsAnswered.get(j).getQid()) {
                    allQuestions.remove(i);
                    i--;
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
