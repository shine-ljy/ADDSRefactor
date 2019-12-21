package com.java.adds.dao;

import com.java.adds.controller.vo.FilterQuestionVO;
import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.DataSetsEntity;
import com.java.adds.entity.QuestionEntity;
import com.java.adds.mapper.*;
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

    @Autowired
    DataSetsMapper dataSetsMapper;

    @Autowired
    KGMapper kgMapper;

    /**ljy
     * 管理员获取所有医生信息
     */
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorMapper.getAllDoctors();
    }

//    /**ljy
//     *获取所有还没回答的选择题
//     * @return
//     */
//    public ArrayList<QAEntity> getChoiceQuestionsNotAnswered(Long uid, FilterQuestionVO filterQuestionVO)
//    {
//        ArrayList<QAEntity> allQuestions=questionMapper.getChoiceQuestionsNotAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),uid);
//        return allQuestions;
//    }


//    /**ljy
//     *医生获取所有已经回答的选择题问题（分页查询）
//     * @return
//     */
//    public ArrayList<QAEntity> getQuestionsAnswered(FilterQuestionVO filterQuestionVO, Long uid)
//    {
//        return questionMapper.getQuestionAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),uid);
//    }

//    /**ljy
//     * 医生获取所有已经回答的详细解答问题（分页查询）
//     * @return
//     */
//    public ArrayList<QAEntity> getDetailQuestionsAnswered(FilterQuestionVO filterQuestionVO, Long doctorId)
//    {
//        return questionMapper.getDetailQuestionsAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
//    }

//    /**ljy
//     * 医生获取所有还未回答的详细解答题
//     * @return
//     */
//    public ArrayList<QAEntity> getDetailQuestionsNotAnswered(FilterQuestionVO filterQuestionVO, Long doctorId)
//    {
//        return questionMapper.getDetailQuestionsNotAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
//    }

    /**ljy
     * 医生获取问题（回答与否，问题类型）
     * @return
     */
    public ArrayList<QuestionEntity> getFilterQuestion(FilterQuestionVO filterQuestionVO, Long doctorId)
    {
        //1:已经回答，2：还未回答
        //1:选择题，2：详细解答题
        if(filterQuestionVO.getAnsweredOrNot()==1&&filterQuestionVO.getQuestionType()==1)//已经回答的选择题
            return questionMapper.getQuestionAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
        else if(filterQuestionVO.getAnsweredOrNot()==1&&filterQuestionVO.getQuestionType()==2)//已经回答的详细解答题
            return questionMapper.getDetailQuestionsAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
        else if(filterQuestionVO.getAnsweredOrNot()==2&&filterQuestionVO.getQuestionType()==1)//还未回答的选择题
            return questionMapper.getChoiceQuestionsNotAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
        else//还未回答的详细解答题
            return questionMapper.getDetailQuestionsNotAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
    }



    /**ljy
     *获取某一个科室下的未回答问题
     * @return
     */
    public ArrayList<QuestionEntity> getQuestionsInHosDepartment(Long uid, Long hdId)
    {
        ArrayList<QuestionEntity> questions=questionMapper.getQuestionsInHosDepartment(hdId);
        ArrayList<QuestionEntity> questionsAnswered=questionMapper.getAllQuestionAnswered(uid);
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
     * 医生新建一个数据集
     * @return
     */
    public Integer newDataSet(Integer doctorId)
    {
        return dataSetsMapper.newDataSet(doctorId);
    }

    /**ljy
     * 医生上传数据集
     * @return
     */
    public void uploadDataSet(Integer dId,Integer doctorId, String fileName,String filePath,String fileType)
    {
        if(fileType.equals("train"))
            dataSetsMapper.uploadTrainDataSet(dId,doctorId,filePath,fileName);
        else if(fileType.equals("test"))
            dataSetsMapper.uploadTestDataSet(dId,doctorId,filePath,fileName);
        else
            dataSetsMapper.uploadDevDataSet(dId,doctorId,filePath,fileName);
    }

    /**ljy
     * 医生上传知识图谱
     * @return
     */
    public Long uploadKG(Long doctorId,String fileName,String filePath)
    {
        return kgMapper.uploadKG(doctorId,fileName,filePath);
    }

    /**ljy
     * 医生获取数据集
     * @return
     */
    public ArrayList<DataSetsEntity> getDataSets(Long doctorId)
    {
        return dataSetsMapper.getDataSets(doctorId);
    }

    /**ljy
     * 医生获获取知识图谱
     * @return
     */
    public ArrayList<DataSetsEntity> getKGS(Long doctorId)
    {
        return kgMapper.getKGS(doctorId);
    }
}
