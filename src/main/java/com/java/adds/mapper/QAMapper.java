package com.java.adds.mapper;


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
     *根据问题id查找答案
     * @return
     */
    public ArrayList<Integer> searchAnswerById(@Param("qid") Long qid);

    /**ljy
     *查找自己提交的所有问题
     * @return
     */
    public ArrayList<QAEntity> searchMyQuestion(@Param("uid")Long uid);

    /**ljy
     *获取所有问题
     * @return
     */
    public ArrayList<QAEntity> getAllQuestions();
}
