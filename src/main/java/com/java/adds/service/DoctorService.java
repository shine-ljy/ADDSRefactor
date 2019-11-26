package com.java.adds.service;

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
     *获取所有还没回答的问题
     * @return
     */
    public ArrayList<QAEntity> getQuestionsNotAnswered(Long uid)
    {
        return doctorDao.getQuestionsNotAnswered(uid);
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
}
