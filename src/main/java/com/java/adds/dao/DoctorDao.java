package com.java.adds.dao;

import com.java.adds.controller.vo.FilterQuestionVO;
import com.java.adds.controller.vo.QuestionAnswerVO;
import com.java.adds.entity.*;
import com.java.adds.mapper.*;
import com.java.adds.utils.EmailUtil;
import com.java.adds.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Component
public class DoctorDao {
    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionResultMapper questionResultMapper;

    @Autowired
    QuestionDetailAnswerMapper questionDetailAnswerMapper;

    @Autowired
    DataSetsMapper dataSetsMapper;

    @Autowired
    KGMapper kgMapper;

    @Autowired
    DeepModelTaskMapper deepModelTaskMapper;

    @Autowired
    EmailUtil emailUtil;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    DeepModelMapper deepModelMapper;

    @Autowired
    DeepModelTaskResultMapper deepModelTaskResultMapper;

    //    @Value("E://医疗项目//大创//ADDS重构//ADDS//src//main//resources//dataSets//")
//    String dataSetsPathInServer;

    @Value("/home/lf/桌面/SIGIR_QA/HAR-master/data/pinfo/hqa-sample/")
    String dataSetsPathInServer;

    @Value("main.py --phase train --model_file examples/pinfo/config/")
    String type1PythonPath;  //运行模型命令的前缀路径

    @Value("home/lf/桌面/SIGIR_OA/HAR-master/matchzoo/")
    String deepModelPath;  //运行模型命令的前缀路径


    //模型运行结果路径
    //E://医疗项目//大创//ADDS重构//ADDS//src//main//resources//DMResultFile//
    @Value("C:\\Users\\yin\\Desktop\\")
    String deepModelResultPathInServer;

    @Value("/ADDS/deepModelResult/**")
    String deepModelResultPath;

    /**ljy
     * 管理员获取所有医生信息
     */
    public ArrayList<DoctorEntity> getAllDoctors()
    {
        return doctorMapper.getAllDoctors();
    }

    /**ljy
     * 医生获取问题（回答与否，问题类型）
     * @return
     */
    public ArrayList<QuestionEntity> getFilterQuestion(FilterQuestionVO filterQuestionVO, Long doctorId)
    {
        //1:已经回答，2：还未回答
        //1:选择题，2：详细解答题
        if(filterQuestionVO.getAnsweredOrNot()==1&&filterQuestionVO.getQuestionType()==1)//已经回答的选择题
            return questionMapper.getQuestionAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
        else if(filterQuestionVO.getAnsweredOrNot()==1&&filterQuestionVO.getQuestionType()==2)//已经回答的详细解答题
            return questionMapper.getDetailQuestionsAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
        else if(filterQuestionVO.getAnsweredOrNot()==2&&filterQuestionVO.getQuestionType()==1)//还未回答的选择题
            return questionMapper.getChoiceQuestionsNotAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
        else//还未回答的详细解答题
            return questionMapper.getDetailQuestionsNotAnswered((filterQuestionVO.getStart()-1)* filterQuestionVO.getLimit(), filterQuestionVO.getLimit(),doctorId);
    }



    /**ljy
     *获取某一个科室下的未回答问题
     * @return
     */
    public ArrayList<QuestionEntity> getQuestionsInHosDepartment(Long uid, Long hdId)
    {
        ArrayList<QuestionEntity> questions=questionMapper.getQuestionsInHosDepartment(hdId);
        ArrayList<QuestionEntity> questionsAnswered=questionMapper.getAllQuestionAnswered(uid);
        for(int i=0;i<questions.size();i++) {
            for (int j = 0; j < questionsAnswered.size(); j++) {
                if (questions.get(i).getQid() == questionsAnswered.get(j).getQid()) {
                    questions.remove(i);
                    i--;
                    break;
                }
            }
        }
        return questions;
    }

    /**ljy
     * 医生回答某个问题
     * @return
     */
    public boolean insertAnswer(Long uid, Long qid, QuestionAnswerVO questionAnswerVO)
    {
        if(questionAnswerVO.getType()==1)  //选择题
        {
            int answer=0;
            if(questionAnswerVO.getAnswer().equals("yes"))  //1:yes,2:no
                answer=1;
            else
                answer=2;
            questionResultMapper.insertChoiceAnswer(uid,qid,answer, questionAnswerVO.getRemark());
        }
        else
            questionDetailAnswerMapper.insertDetailAnswer(uid,qid, questionAnswerVO.getAnswer(), questionAnswerVO.getRemark());

        return true;
    }


    /**ljy
     * 医生新建一个数据集
     * @return
     */
    public Integer newDataSet(Integer doctorId, DataSetsEntity dataSetsEntity)
    {
        dataSetsMapper.newDataSet(doctorId, dataSetsEntity);
        return Math.toIntExact(dataSetsEntity.getId());
    }


    /**ljy
     * 医生上传数据集
     * @return
     */
    public void uploadDataSet(Integer dId,Integer doctorId, String fileName,String filePath,String fileType)
    {
        if(fileType.equals("train"))
            dataSetsMapper.uploadTrainDataSet(dId,doctorId,filePath,fileName);
        else if(fileType.equals("test"))
            dataSetsMapper.uploadTestDataSet(dId,doctorId,filePath,fileName);
        else
            dataSetsMapper.uploadDevDataSet(dId,doctorId,filePath,fileName);
    }

    /**ljy
     * 医生上传知识图谱
     * @return
     */
//    public Long uploadKG(Long doctorId,String fileName,String filePath)
//    {
//        return kgMapper.uploadKG(doctorId,fileName,filePath);
//    }

    /**ljy
     * 医生获取全部数据集
     * @return
     */
    public ArrayList<DataSetsEntity> getDataSets(Long doctorId) {
        return dataSetsMapper.getDataSets(doctorId);
    }

    /**ljy
     * 医生获取可用数据集
     * @return
     */
    public ArrayList<DataSetsEntity> getAvailableDataSets(Long doctorId) {
        return dataSetsMapper.getAvailableDataSets(doctorId);
    }

    /**ljy
     * 医生获获取知识图谱
     * @return
     */
//    public ArrayList<DataSetsEntity> getKGS(Long doctorId)
//    {
//        return kgMapper.getKGS(doctorId);
//    }


    /**ljy
     * 医生运行一个深度学习模型
     * @return
     */
    @Async
    public void doDeepModelTask(Long doctorId, DeepModelTaskEntity deepModelTaskEntity)  //异步线程调用
    {
        //向数据库中插入一条深度学习模型信息
        Long taskId=deepModelTaskMapper.doDeepModelTask(doctorId,deepModelTaskEntity.getTaskName(),deepModelTaskEntity.getDatasetId(),deepModelTaskEntity.getKgId(),deepModelTaskEntity.getModelId(),deepModelTaskEntity.getMetricId(),0);
        //查找是否已经有了相同的模型运行结果
        ArrayList<DeepModelTaskEntity> tempDeepModelTask=deepModelTaskMapper.getSimilarityModelTask(deepModelTaskEntity.getDatasetId(),deepModelTaskEntity.getKgId(),deepModelTaskEntity.getModelId(),deepModelTaskEntity.getMetricId());
        Long taskResultId=null;
        String taskResultFilePath="";  //模型结果文件路径
        if(tempDeepModelTask==null)  //没有找到相同的模型结果
        {
            DataSetsEntity tempDataSet=dataSetsMapper.getDataSetsById(deepModelTaskEntity.getDatasetId());
            String train=dataSetsPathInServer+tempDataSet.getTrain_name();
            String test=dataSetsPathInServer+tempDataSet.getTest_name();
            String dev=dataSetsPathInServer+tempDataSet.getDev_name();
            String dstdir=dataSetsPathInServer;
            String modelDstDir=dataSetsPathInServer;
            //生成配置文件
            fileUtil.createPythonConfig(train,test,dev,dstdir,modelDstDir);

            //运行.sh文件,执行模型运行代码
            try {
                String[] cmd = {"chmod","/home/lf/桌面/SIGIR_QA/HAR-master/data/pinfo/run_data.sh"};
                Runtime rt = Runtime.getRuntime();
                rt.exec(cmd);

                //运行深度学习模型
                DeepModelEntity deepModelEntity=deepModelMapper.getModelById(deepModelTaskEntity.getModelId());
                String exe="python";
                String command="";
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                Date date = new Date();
                String nowData = format.format(date);
                String outputPath=deepModelPath+"output/"+deepModelTaskEntity.getId().toString()+nowData+".txt";  //模型结果存放的路径

                if(deepModelTaskEntity.getModelType()==1)  //first type Feature-based
                    command="python"+deepModelPath+type1PythonPath+deepModelEntity.getConfigFile()+" >>"+outputPath;
                else if(deepModelTaskEntity.getModelType()==2)
                    ;
                else
                    ;
                String[] cmdArr = new String[] {"sh","-c","conda activate pytorch"};
                Runtime.getRuntime().exec(cmdArr);  //切换服务器环境
                cmdArr = new String[] {"sh","-c",command};
                Process process = Runtime.getRuntime().exec(cmdArr);//模型运行

                //从文本中提取出结果存入数据库
                String result="";
                DeepModelTaskResultEntity deepModelTaskResultEntity=new DeepModelTaskResultEntity();
                FileReader fileReader = null;
                try
                {
                    fileReader = new FileReader(outputPath);//"C:\\Users\\yin\\Desktop\\res.txt"
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                Scanner sc = new Scanner(fileReader);
                String line = null;
                while((sc.hasNextLine()&&(line=sc.nextLine())!=null))
                {
                    if(!sc.hasNextLine()){
                        result=line;
                    }
                }
                sc.close();
                String temp[]=result.split("\t");
                deepModelTaskResultEntity.setNdcg1(temp[2].split("=")[1]);
                deepModelTaskResultEntity.setNdcg3(temp[3].split("=")[1]);
                deepModelTaskResultEntity.setNdcg5(temp[4].split("=")[1]);
                deepModelTaskResultEntity.setNdcg10(temp[5].split("=")[1]);
                deepModelTaskResultEntity.setMap(temp[6].split("=")[1]);
                deepModelTaskResultEntity.setRecall3(temp[7].split("=")[1]);
                deepModelTaskResultEntity.setRecall5(temp[8].split("=")[1]);
                deepModelTaskResultEntity.setRecall10(temp[9].split("=")[1]);
                deepModelTaskResultEntity.setPrecision1(temp[10].split("=")[1]);
                deepModelTaskResultEntity.setPrecision3(temp[11].split("=")[1]);
                deepModelTaskResultEntity.setPrecision5(temp[12].split("=")[1]);
                deepModelTaskResultEntity.setPrecision10(temp[13].split("=")[1]);
                deepModelTaskResultEntity.setTaskId(taskId);

                //结果写入文件
                SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                Date d = new Date();
                String nowTime = f.format(d);
                String resultFileName=doctorId.toString()+nowTime+"-ModelResult.csv";//为了避免文件重名
                String resultFilePath=deepModelResultPathInServer+resultFileName;
                taskResultFilePath=deepModelResultPath+resultFileName;
                File resultFileDest=new File(resultFilePath);
                //创建目录
                if(!resultFileDest.getParentFile().exists()){
                    resultFileDest.getParentFile().mkdir();
                }
                //create a file
                try{
                    resultFileDest.createNewFile();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                FileOutputStream out=null;
                OutputStreamWriter osw=null;
                BufferedWriter bw=null;
                try {
                    out = new FileOutputStream(resultFileDest);
                    osw = new OutputStreamWriter(out,"UTF-8");
                    bw =new BufferedWriter(osw);

                    //写入数据
                    String fileHead="Id,taskId,ndcg@1,ndcg@3,ndcg@5,ndcg@10,map,recall@3,recall@5,recall@10,precision@1,precision@3,precision@5,precision@10";
                    bw.append(fileHead);
                    //换行符
                    bw.append("\r");
                    String re=doctorId.toString()+","+taskId.toString()+","+deepModelTaskResultEntity.getNdcg1()+","+deepModelTaskResultEntity.getNdcg3()+","+deepModelTaskResultEntity.getNdcg5()+","+deepModelTaskResultEntity.getNdcg10()+","+deepModelTaskResultEntity.getMap()+","+deepModelTaskResultEntity.getRecall3()+","+deepModelTaskResultEntity.getRecall5()+","+deepModelTaskResultEntity.getRecall10()+","+deepModelTaskResultEntity.getPrecision1()+","+deepModelTaskResultEntity.getPrecision3()+","+deepModelTaskResultEntity.getPrecision5()+","+deepModelTaskResultEntity.getPrecision10();
                    bw.append("\r");

                } catch (Exception e) {

                }finally{
                    if(bw!=null){
                        try {
                            bw.close();
                            bw=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(osw!=null){
                        try {
                            osw.close();
                            osw=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(out!=null){
                        try {
                            out.close();
                            out=null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if(resultFileDest.exists()&&resultFileDest.length()>0){
                    System.out.println("ok");
                }
                taskResultId=deepModelTaskResultMapper.insertDeepModelTaskResult(deepModelTaskResultEntity);//将结果插入数据库

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //修改数据库信息
            deepModelTaskMapper.updateTask(taskId,1,taskResultId,taskResultFilePath);
        }
        else
            deepModelTaskMapper.updateTask(taskId,1,tempDeepModelTask.get(0).getResultId(),tempDeepModelTask.get(0).getResultFilePath());

        //向用户发送模型运行完毕的邮件
        DoctorEntity doctorEntity=doctorMapper.getDoctorById(doctorId);
        emailUtil.sendSimpleEmail("ADDS system task completion notification","You have a new completed task, please log in the ADDS system for viewing!",doctorEntity.getEmail());
    }

    /**
     * 医生获取所有任务
     * @author ljy
     * @return
     */
    public ArrayList<DeepModelTaskEntity> getDMTasks(Integer doctorId)
    {
        return deepModelTaskMapper.getDMTasks(doctorId);
    }
}
