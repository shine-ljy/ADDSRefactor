package com.java.adds.controller;

import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.controller.vo.FilterQuestionVO;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.QuestionEntity;
import com.java.adds.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    /**ljy
     * 管理员获取所有医生信息
     */
    @GetMapping("")
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorService.getAllDoctors();
    }


    /**ljy
     * 医生获取问题（回答与否，问题类型）
     * @return
     */
    @PostMapping("{doctorId}/question")
    public ArrayList<QuestionEntity> getFilterQuestion(@RequestBody FilterQuestionVO filterQuestionVO, @PathVariable Long doctorId)
    {
        return doctorService.getFilterQuestion(filterQuestionVO,doctorId);
    }

//    /**ljy
//     * 医生获取所有还未回答的选择题
//     * @return
//     */
//    @PostMapping("{doctorId}/choiceQuestion")
//    public ArrayList<QAEntity> getChoiceQuestionsNotAnswered(@RequestBody FilterQuestionVO filterQuestionVO, @PathVariable Long doctorId)
//    {
//        return doctorService.getChoiceQuestionsNotAnswered(doctorId, filterQuestionVO);
//    }

//    /**ljy
//     * 医生获取所有还未回答的详细解答题
//     * @return
//     */
//    @PostMapping("{doctorId}/detailQuestion")
//    public ArrayList<QAEntity> getDetailQuestionsNotAnswered(@RequestBody FilterQuestionVO filterQuestionVO, @PathVariable Long doctorId)
//    {
//        return doctorService.getDetailQuestionsNotAnswered(filterQuestionVO,doctorId);
//    }

//    /**ljy
//     * 医生获取所有已经回答的选择题问题（分页查询）
//     * @return
//     */
//    @PostMapping("{doctorId}/choiceQuestionAnswered")
//    public ArrayList<QAEntity> getQuestionsAnswered(@RequestBody FilterQuestionVO filterQuestionVO, @PathVariable Long doctorId)
//    {
//        return doctorService.getQuestionsAnswered(filterQuestionVO,doctorId);
//    }

//    /**ljy
//     * 医生获取所有已经回答的详细解答问题（分页查询）
//     * @return
//     */
//    @PostMapping("{doctorId}/detailQuestionAnswered")
//    public ArrayList<QAEntity> getDetailQuestionsAnswered(@RequestBody FilterQuestionVO filterQuestionVO, @PathVariable Long doctorId)
//    {
//        return doctorService.getDetailQuestionsAnswered(filterQuestionVO,doctorId);
//    }

    /**ljy
     * 医生获取某一个科室下的未回答问题
     * @return
     */
    @GetMapping("{uid}/question/hospitalDepartment/{hdId}")
    public ArrayList<QuestionEntity> getQuestionsInHosDepartment(@PathVariable Long uid, @PathVariable Long hdId)
    {
        return doctorService.getQuestionsInHosDepartment(uid,hdId);
    }

    /**ljy
     * 医生回答某个问题
     * @return
     */
    @PutMapping("{uid}/question/{qid}/answer")
    public boolean insertAnswer(@PathVariable Long uid, @PathVariable Long qid, @RequestBody QuestionAnswerVO questionAnswerVO)
    {
        return doctorService.insertAnswer(uid,qid, questionAnswerVO);
    }
}
