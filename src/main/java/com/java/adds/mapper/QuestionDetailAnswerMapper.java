package com.java.adds.mapper;


import com.java.adds.entity.QAAnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface QuestionDetailAnswerMapper {

    /**ljy
     *根据问题id查找所有的详细回答
     * @return
     */
    public ArrayList<QAAnswerEntity> searchDetailAnswerById(@Param("qid") Long qid);

    /**ljy
     * 医生回答某个问题
     * @return
     */
    public boolean insertDetailAnswer(@Param("uid") Long uid, @Param("qid") Long qid, @Param("answer") String answer,@Param("remark") String remark);

}
