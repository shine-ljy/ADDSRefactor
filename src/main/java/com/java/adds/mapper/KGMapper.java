package com.java.adds.mapper;


import com.java.adds.entity.DataSetsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface KGMapper {
    /**ljy
     * 医生上传知识图谱
     * @return
     */
    public Long uploadKG(@Param("uId") Long doctorId, @Param("fileName") String fileName, @Param("filePath") String filePath);

    /**ljy
     * 医生获获取知识图谱
     * @return
     */
    public ArrayList<DataSetsEntity> getKGS(@Param("uId") Long doctorId);

}
