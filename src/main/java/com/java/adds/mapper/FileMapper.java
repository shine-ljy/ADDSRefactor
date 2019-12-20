package com.java.adds.mapper;


import com.java.adds.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface FileMapper {

    /**ljy
     * 医生上传数据集
     * @return
     */
    public Long uploadFile(@Param("uid") Long doctorId, @Param("fileName") String fileName, @Param("filePath") String filePath,@Param("fileType")String fileType);

    /**ljy
     * 医生获取数据集
     * @return
     */
    public ArrayList<FileEntity> getFiles(@Param("uid") Long doctorId, @Param("fileType") String fileType);
}
