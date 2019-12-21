package com.java.adds.service;

import com.java.adds.controller.vo.FilterQuestionVO;
import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.dao.DoctorDao;
import com.java.adds.entity.DataSetsEntity;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
     * 医生获取问题（回答与否，问题类型）
     * @return
     */
    public ArrayList<QuestionEntity> getFilterQuestion(FilterQuestionVO filterQuestionVO, Long doctorId)
    {
        return doctorDao.getFilterQuestion(filterQuestionVO,doctorId);
    }

//    /**ljy
//     *获取所有还没回答的选择题
//     * @return
//     */
//    public ArrayList<QAEntity> getChoiceQuestionsNotAnswered(Long uid, FilterQuestionVO filterQuestionVO)
//    {
//        return doctorDao.getChoiceQuestionsNotAnswered(uid, filterQuestionVO);
//    }

//    /**ljy
//     *医生获取所有已经回答的选择题问题（分页查询）
//     * @return
//     */
//    public ArrayList<QAEntity> getQuestionsAnswered(FilterQuestionVO filterQuestionVO, Long doctorId)
//    {
//        return doctorDao.getQuestionsAnswered(filterQuestionVO,doctorId);
//    }

//    /**ljy
//     * 医生获取所有已经回答的详细解答问题（分页查询）
//     * @return
//     */
//    public ArrayList<QAEntity> getDetailQuestionsAnswered(FilterQuestionVO filterQuestionVO, Long doctorId)
//    {
//        return doctorDao.getDetailQuestionsAnswered(filterQuestionVO,doctorId);
//    }

//    /**ljy
//     * 医生获取所有还未回答的详细解答题
//     * @return
//     */
//    public ArrayList<QAEntity> getDetailQuestionsNotAnswered(FilterQuestionVO filterQuestionVO, Long doctorId)
//    {
//        return doctorDao.getDetailQuestionsNotAnswered(filterQuestionVO,doctorId);
//    }

    /**ljy
     *获取某一个科室下的未回答问题
     * @return
     */
    public ArrayList<QuestionEntity> getQuestionsInHosDepartment(Long uid, Long hdId)
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
     * 医生新建一个数据集
     * @return
     */
    public Integer newDataSet(Integer doctorId)
    {
        return doctorDao.newDataSet(doctorId);
    }

    /**ljy
     * 医生上传数据集
     * @return
     */
    public void uploadDataSet(Integer dId,Integer doctorId, String fileName,String filePath,String fileType)
    {
        doctorDao.uploadDataSet(dId,doctorId,fileName,filePath,fileType);
    }


    /**ljy
     * 医生上传知识图谱
     * @return
     */
    public Long uploadKG(Long doctorId,String fileName,String filePath)
    {
        return doctorDao.uploadKG(doctorId,fileName,filePath);
    }

    /**ljy
     * 医生获取数据集
     * @return
     */
    public ArrayList<DataSetsEntity> getDataSets(Long doctorId)
    {
        return doctorDao.getDataSets(doctorId);
    }

    /**ljy
     * 医生获获取知识图谱
     * @return
     */
    public ArrayList<DataSetsEntity> getKGS(Long doctorId)
    {
        return doctorDao.getKGS(doctorId);
    }
}
