package com.java.adds.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("upload")
public class UploadController {
    public final static String IMG_PATH_PREFIX = "src/main/resources/static";
    public static File getRecordFile(String imgPath){
        // 构建上传文件的存放 "文件夹" 路径s
        String fileDirPath = new String("src/main/resources/medicalRecords" + imgPath);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }


//    /**
//     * 上传新闻缩略图
//     * @param file
//     * @return
//     */
//    @CrossOrigin
//    @ApiOperation(httpMethod = "POST", value = "上传新闻缩略图", notes = "上传新闻缩略图", produces = "application/json;charset=UTF-8")
//    @ApiImplicitParam(name = "file", value = "文件名", required = true, paramType = "query")
//    @PostMapping("/uploadSLT")
//    public Object uploadSLT(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
//        Map map = new HashMap();
//        if (file.isEmpty()) {
//            map.put("error", "上传失败，请选择文件");
//        }
//        //获取文件名
//        String fileName = file.getOriginalFilename();
//        String prefix = fileName.substring(fileName.lastIndexOf("."));//文件后缀
//        //新文件名
//        String s = IdUtil.simpleUUID();//避免重复以UUID为文件名
//        String filenas = s + prefix;
//        // 存放上传图片的文件夹
//        File fileDir = getImgDirFile(IMG_PATH_PREFIX);
//        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
//        String absolutePath = fileDir.getAbsolutePath();
//        File dest = new File(absolutePath + File.separator+ filenas);
//        try {
//            file.transferTo(dest);
//            LOGGER.info("上传成功");
//            //拼接地址
//            String url = filePath + filenas;
//            return MapMess.success(url);
//        } catch (IOException e) {
//            LOGGER.error(e.toString(), e);
//            return MapMess.error(HttpRes.EXCEPTION.val(),HttpRes.EXCEPTION.msg());
//        }
//    }

}
