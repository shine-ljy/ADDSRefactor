package com.java.adds.controller;

import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.controller.vo.SetPage;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.QAEntity;
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
     * 医生获取所有还未回答的选择题
     * @return
     */
    @PostMapping("{doctorId}/choiceQuestion")
    public ArrayList<QAEntity> getChoiceQuestionsNotAnswered(@RequestBody SetPage setPage, @PathVariable Long doctorId)
    {
        return doctorService.getChoiceQuestionsNotAnswered(doctorId,setPage);
    }

    /**ljy
     * 医生获取所有还未回答的详细解答题
     * @return
     */
    @PostMapping("{doctorId}/detailQuestion")
    public ArrayList<QAEntity> getDetailQuestionsNotAnswered(@RequestBody SetPage setPage, @PathVariable Long doctorId)
    {
        return doctorService.getDetailQuestionsNotAnswered(setPage,doctorId);
    }

    /**ljy
     * 医生获取所有已经回答的问题
     * @return
     */
    @GetMapping("{doctorId}/questionAnswered")
    public ArrayList<QAEntity> getQuestionsAnswered(@PathVariable Long doctorId)
    {
        return doctorService.getQuestionsAnswered(doctorId);
    }

    /**ljy
     * 医生获取某一个科室下的未回答问题
     * @return
     */
    @GetMapping("{uid}/question/hospitalDepartment/{hdId}")
    public ArrayList<QAEntity> getQuestionsInHosDepartment(@PathVariable Long uid,@PathVariable Long hdId)
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
