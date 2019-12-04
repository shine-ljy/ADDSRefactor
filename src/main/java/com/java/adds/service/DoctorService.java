package com.java.adds.service;

import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.controller.vo.SetPage;
import com.java.adds.dao.DoctorDao;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.QAEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoctorService {
    @Autowired
    DoctorDao doctorDao;

    /**ljy
     * 管理员获取所有医生信息
     */
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorDao.getAllDoctors();
    }

    /**ljy
     *获取所有还没回答的选择题
     * @return
     */
    public ArrayList<QAEntity> getChoiceQuestionsNotAnswered(Long uid, SetPage setPage)
    {
        return doctorDao.getChoiceQuestionsNotAnswered(uid,setPage);
    }

    /**ljy
     *医生获取所有已经回答的问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsAnswered(Long uid)
    {
        return doctorDao.getQuestionsAnswered(uid);
    }

    /**ljy
     *获取某一个科室下的未回答问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsInHosDepartment(Long uid,Long hdId)
    {
        return doctorDao.getQuestionsInHosDepartment(uid,hdId);
    }

    /**ljy
     * 医生回答某个问题
     * @return
     */
    public boolean insertAnswer(Long uid, Long qid, QuestionAnswerVO questionAnswerVO)
    {
        return doctorDao.insertAnswer(uid,qid, questionAnswerVO);
    }

    /**ljy
     * 医生获取所有还未回答的详细解答题
     * @return
     */
    public ArrayList<QAEntity> getDetailQuestionsNotAnswered(SetPage setPage, Long doctorId)
    {
        return doctorDao.getDetailQuestionsNotAnswered(setPage,doctorId);
    }
}
