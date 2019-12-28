package com.java.adds.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**ljy
 *上传文件check
 */
@Component
public class FileCheckUtil {
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
}
