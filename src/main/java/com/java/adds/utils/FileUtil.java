package com.java.adds.utils;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;


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

    public void createPythonConfig(String train,String test,String dev,String dstdir,String modelDstDir) {
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

    /**
     * QXL
     * 判断文件类型（后缀名）是否是 csv 文件
     * @param file file
     * @return true / false
     */
    public boolean csvFileType(File file) {
        String fileName = file.getName();
        if (fileName.contains(".")) {
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            return fileType.toLowerCase().equals("csv");
        }
        return false;
    }

    /**
     * QXL
     * 判断 KG 文件（前提是 csv 文件）的数据格式是否正确
     * 目前判断规则是：csv 文件必须有header，必须有 3 列，且为 header / relation / tail
     * 否则均视为格式错误
     * @param file file
     * @return true / false
     */
    public boolean kgFileFormat(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] csvHeaders = reader.readLine().split(",");
            if (csvHeaders.length == 3) {
                return csvHeaders[0].equals("header") && csvHeaders[1].equals("relation") && csvHeaders[2].equals("tail");
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * QXL
     * 删除文件
     * @param file file
     */
    public void deleteFile(File file) {
        if (file.exists()) {
            boolean result = file.delete();
            if (result) {
                System.out.println("File is deleted successfully! ");
            } else {
                System.out.println("Fail to delete file... ");
            }
        } else {
            System.out.println("File is non-exist. ");
        }
    }

    /**
     * QXL
     * 数据导出至 Excel 表格
     * @param outputStream HttpServletResponse outputStream
     * @param data Task result data
     * @param sheetName Excel sheet name
     * @param columnWidth Excel sheet column width
     */
    public static void exportDataToExcel(OutputStream outputStream, List<List<Object>> data, String sheetName, int columnWidth) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(columnWidth);

        int rowIndex = 0;
        for (List<Object> rowData : data) {
            HSSFRow row = sheet.createRow(rowIndex++);
            for (int i = 0; i < rowData.size(); i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString((String) rowData.get(i));
                cell.setCellValue(text);
            }
        }
        workbook.write(outputStream);
        workbook.close();
    }
}
