package com.java.adds.mapper;


import com.java.adds.entity.QAAnswerEntity;
import com.java.adds.entity.QAEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface QAMapper {

    /**ljy
     *新增question
     * @return
     */
    public boolean addQuestion(QAEntity qaEntity);

    /**ljy
     *根据id删除question
     * @return
     */
    public boolean deleteQuestion(@Param("qid") Long qid);

    /**ljy
     *根据id删除question对应的回答
     * @return
     */
    public boolean deleteAnswersByQid(@Param("qid") Long qid);

    /**ljy
     *根据问题id查找答案
     * @return
     */
    public ArrayList<Integer> searchChoiceAnswerById(@Param("qid") Long qid);

    /**ljy
     *查找自己提交的所有问题
     * @return
     */
    public ArrayList<QAEntity> searchMyQuestion(@Param("uid")Long uid);

    /**ljy
     *获取所有问题
     * @return
     */
    public ArrayList<QAEntity> getAllQuestions(@Param("start") Integer start, @Param("limitRecord") Integer limit);

    /**ljy
     *获取已经回答的问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionAnswered(@Param("uid") Long uid);

    /**ljy
     *获取某一个科室下的所有问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsInHosDepartment(Long hdId);

    /**ljy
     *根据问题id查找所有的详细回答
     * @return
     */
    public ArrayList<QAAnswerEntity> searchDetailAnswerById(@Param("qid") Long qid);

    /**ljy
     *根据问题id查找QAEntity
     * @return
     */
    public QAEntity getQuestionById(@Param("qid") Long qid);
}
