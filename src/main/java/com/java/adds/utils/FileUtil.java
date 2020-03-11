package com.java.adds.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;


/**ljy
 *上传文件check
 */
@Component
public class FileUtil {
    @Value("/home/lf/桌面/SIGIR_QA/HAR-master/data/pinfo/config.py")
    String fileToBeChange;

    public boolean checkDataset(File file)//检查数据集格式
    {
        int flag=0;
        try{
            BufferedReader in= new BufferedReader(new FileReader(file));
            String[] texts=in.readLine().split("\t");
            if(texts.length!=3||texts[0].length()!=1||!Character.isDigit(texts[0].charAt(0)))//格式错误
                flag=1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(flag==1)
            return false;
        else
            return true;
    }

    public void createPythonConfig(String train,String test,String dev,String dstdir,String modelDstDir)
    {
        try {
            File file =new File(fileToBeChange);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName());
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("{"+"\n");
            bufferWritter.write("\t"+"\"dstdir\" : "+dstdir+",\n");
            bufferWritter.write("\t"+"\"traindir\":"+train+",\n");
            bufferWritter.write("\t"+"\"testdir\":"+test+",\n");
            bufferWritter.write("\t"+"\"devdir\":"+dev+",\n");
            bufferWritter.write("\t"+"\"model_dst_dir\" : "+modelDstDir+",\n");
            bufferWritter.write("\t"+"\"text1_maxlen\": 20,"+"\n");
            bufferWritter.write("\t"+"\"text2_maxlen\": 300,"+"\n");
            bufferWritter.write("\t"+"\"base_metric\":\"map\","+"\n");
            bufferWritter.write("\t"+"\"weights_dir\":\"examples/pinfo/hqa_sample/\","+"\n");
            bufferWritter.write("\t"+"\"metrics\": [ \"ndcg@1\", \"ndcg@3\", \"ndcg@5\", \"ndcg@10\", \"map\", \"recall@3\", \"recall@3\", \"recall@5\", \"recall@10\", \"precision@1\", \"precision@3\", \"precision@5\", \"precision@10\""+"\n");
            bufferWritter.write("}"+"\n");

            bufferWritter.close();

            System.out.println("Done");
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public boolean checkKG(File file)
    {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\yin\\Desktop\\test.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line="";
        try{
            line=bufferedReader.readLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(line.split(",").length==3)
            return true;
        else
            return false;
    }
}

