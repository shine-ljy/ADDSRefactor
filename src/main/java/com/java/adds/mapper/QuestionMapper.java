package com.java.adds.mapper;


import com.java.adds.entity.QAEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface QuestionMapper {

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
     *查找自己提交的所有问题
     * @return
     */
    public ArrayList<QAEntity> searchMyQuestion(@Param("uid") Long uid);

    /**ljy
     *获取所有还没回答的选择题
     * @return
     */
    public ArrayList<QAEntity> getChoiceQuestionsNotAnswered(@Param("start") Integer start, @Param("limitRecord") Integer limit,@Param("uid") Long uid);

    /**ljy
     *获取已经回答的问题
     * @return
     */
    public ArrayList<QAEntity> getAllQuestionAnswered(@Param("uid") Long uid);

    /**ljy
     *获取所有已经回答的选择题问题（分页查询）
     * @return
     */
    public ArrayList<QAEntity> getQuestionAnswered(@Param("start") Integer start, @Param("limitRecord") Integer limit,@Param("uid") Long uid);


    /**ljy
     * 医生获取所有已经回答的详细解答问题（分页查询）
     * @return
     */
    public ArrayList<QAEntity> getDetailQuestionsAnswered(@Param("start") Integer start, @Param("limitRecord") Integer limit,@Param("uid") Long uid);

    /**ljy
     *根据问题id查找QAEntity
     * @return
     */
    public QAEntity getQuestionById(@Param("qid") Long qid);

    /**ljy
     *获取某一个科室下的所有问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsInHosDepartment(@Param("hdId") Long hdId);

    /**ljy
     * 医生获取所有还未回答的详细解答题
     * @return
     */
    public ArrayList<QAEntity> getDetailQuestionsNotAnswered(@Param("start") Integer start, @Param("limitRecord") Integer limit,@Param("uid") Long doctorId);
}
