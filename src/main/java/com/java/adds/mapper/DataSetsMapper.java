package com.java.adds.mapper;


import com.java.adds.entity.DataSetsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface DataSetsMapper {
    /**ljy
     * 医生新建一个数据集
     * @return
     */
    public Integer newDataSet(@Param("uId") Integer doctorId);

    /**ljy
     * 医生上传数据集-train
     * @return
     */
    public void uploadTrainDataSet(@Param("dId") Integer dId,@Param("uId") Integer doctorId,@Param("filePath") String filePath,@Param("fileName") String fileName);

    /**ljy
     * 医生上传数据集-test
     * @return
     */
    public void uploadTestDataSet(@Param("dId") Integer dId,@Param("uId") Integer doctorId, @Param("filePath") String filePath,@Param("fileName") String fileName);

    /**ljy
     * 医生上传数据集-dev
     * @return
     */
    public void uploadDevDataSet(@Param("dId") Integer dId,@Param("uId") Integer doctorId, @Param("filePath") String filePath,@Param("fileName") String fileName);


    /**ljy
     * 医生获取数据集
     * @return
     */
    public ArrayList<DataSetsEntity> getDataSets(@Param("uId") Long doctorId);
}
