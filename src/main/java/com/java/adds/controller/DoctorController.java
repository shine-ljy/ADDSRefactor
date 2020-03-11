package com.java.adds.controller;

import com.java.adds.controller.vo.FilterQuestionVO;
import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.entity.DataSetsEntity;
import com.java.adds.entity.DeepModelTaskEntity;
import com.java.adds.entity.DoctorEntity;
import com.java.adds.entity.QuestionEntity;
import com.java.adds.service.DoctorService;
import com.java.adds.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    FileUtil fileUtil;


//    @Value("E://医疗项目//大创//ADDS重构//ADDS//src//main//resources//dataSets//")
//    String dataSetsPathInServer;

//    @Value("/home/lf/桌面/SIGIR_QA/HAR-master/data/pinfo/hqa-sample/")
//    String dataSetsPathInServer;

    @Value("C://Users//302//Desktop//addsFileUploadTesting//")
    String dataSetsPathInServer;

    @Value("/ADDS/dataSets/**")
    String dataSetsPath;

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

    /**ljy
     * 医生新建一个数据集
     * @return
     */
    @PostMapping("{doctorId}/dataSet")
    public Integer newDataSet(@PathVariable Integer doctorId, @RequestBody DataSetsEntity dataSetsEntity)
    {
        return doctorService.newDataSet(doctorId, dataSetsEntity);
    }

    /**ljy
     * 医生上传数据集
     * @return
     */
    @PostMapping("{doctorId}/dataSets")
    public void uploadDataSet(HttpServletResponse httpServletResponse, @PathVariable Integer doctorId, MultipartFile file, Integer dId, String type)
    {//类型包括train，test，dev
        String fileName=new String();
        try {
            fileName=file.getOriginalFilename();  //获取原始文件名
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            String nowData = format.format(date);
            //dataSetsPathInServer+doctorId.toString()+nowData+"//";
            fileName=doctorId.toString()+nowData+"-"+fileName;//为了避免文件重名
            String filePath=dataSetsPathInServer+fileName;
            File dest=new File(filePath);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            if(fileUtil.checkDataset(dest)==false)  //检查文件格式
            {
                httpServletResponse.setStatus(400,"文件格式错误");
                return;
            }

            file.transferTo(dest);  //将文件保存到服务器

            doctorService.uploadDataSet(dId,doctorId,fileName,dataSetsPath+fileName,type);
        } catch (IOException e) {
            httpServletResponse.setStatus(302,"文件上传失败");
        }
        httpServletResponse.setStatus(200,"文件上传成功");
    }

    /**ljy
     * 医生上传知识图谱
     * @return
     */
    @PostMapping("{doctorId}/kg")
    public void uploadKG(HttpServletResponse httpServletResponse, @PathVariable Long doctorId, MultipartFile file)
    {
        Long fileId=null;
        try {
            String fileName=file.getOriginalFilename();  //获取原始文件名
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            String nowData = format.format(date);
            fileName=doctorId.toString()+nowData+fileName;  //为了避免文件重名
            String filePath=dataSetsPathInServer+fileName;
            File dest=new File(filePath);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            if(fileUtil.checkKG(dest)==false)  //检查文件格式
            {
                httpServletResponse.setStatus(400,"文件格式错误");
                return;
            }
            file.transferTo(dest);  //将文件保存到服务器
            fileId=doctorService.uploadKG(doctorId,fileName,dataSetsPath+fileName);
            //此处还缺少知识图谱的整理和导入代码
        } catch (IOException e) {
            httpServletResponse.setStatus(302,"文件上传失败");
        }
        httpServletResponse.setStatus(200,"文件上传成功");
       // return fileId;
    }

    /**ljy
     * 医生获取数据集(可用)
     * @return
     */
    @GetMapping("{doctorId}/dataSets")
    public ArrayList<DataSetsEntity> getDataSets(@PathVariable Long doctorId)
    {
        return doctorService.getDataSets(doctorId);
    }

    /**ljy
     * 医生获获取知识图谱
     * @return
     */
    @GetMapping("{doctorId}/kg")
    public ArrayList<DataSetsEntity> getKGS(@PathVariable Long doctorId)
    {
        return doctorService.getKGS(doctorId);
    }


    /**ljy
     * 医生运行一个深度学习模型
     * @return
     */
    @PostMapping("{doctorId}/DLTask")
    public void doDeepModelTask(@PathVariable Integer doctorId, @RequestBody DeepModelTaskEntity deepModelTaskEntity)
    {
        doctorService.doDeepModelTask(doctorId,deepModelTaskEntity);
    }

    /**
     * 医生获取所有任务
     * @author ljy
     * @return
     */
    @GetMapping("{doctorId}/DLTask")
    public ArrayList<DeepModelTaskEntity> getDMTasks(@PathVariable Integer doctorId)
    {
        return doctorService.getDMTasks(doctorId);
    }


}
