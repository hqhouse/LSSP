package com.emi.appclock.until;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.emi.appclock.entity.responsebean.ResultBeanObj;
import com.emi.appclock.listener.PutObjectProgressListener;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UploadUtil {

  /*  public static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    public static String accessKeyId = "LTAIbVb8FBYtKCDw";
    public static String accessKeySecret = "tJwCYFVDV9WBw32IJaOQxP2NAOSJtz";
    public static String bucketName = "aixinhrms";
    public static String getAuntOssUrl="https://aixinhrms.oss-cn-shanghai.aliyuncs.com";*/

//    public static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
//    public static String accessKeyId = "LTAI4Fm4KWQht2kCK8y2oepW";
//    public static String accessKeySecret = "VDK3iMm5gRGTqqCOyPiFR5pktbVCZi";
//    public static String bucketName = "ax-hrms";
    public static String getAuntOssUrl="https://ax-hrms.oss-cn-shanghai.aliyuncs.com";
    public static String fileUrl="";

    public static String endpoint = "oss-cn-shanghai.aliyuncs.com";
    public static String accessKeyId = "LTAI4GKzKtgvyuAjp24fGLNT";
    public static String accessKeySecret = "zJwgWNmSgrvgIcWaavvsztyL0RBDTf";
    public static String bucketName = "jianlvmediate";
    public static ResultBeanObj uploadFile(MultipartFile file, HttpServletRequest request, String type) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Map map=new HashMap();
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf("."));
            String newName =UUID.randomUUID().toString() +extName;
            Date nowDate = new Date();
            String year  = StringUtils.dateToString(nowDate, "yyyy");
            String month = StringUtils.dateToString(nowDate, "MM");
            String imgurlnew= "/upload/";
            String imgurlnews= "upload/";
            String key=createFolder(ossClient,bucketName,imgurlnews);
            // 转存文件
            String filePath=imgurlnews+newName;
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(new PutObjectRequest(bucketName, imgurlnews + newName, inputStream));
            map.put("imageUrl",imgurlnew+newName);
            map.put("fileName",fileName);
            map.put("shortUrl",imgurlnew+newName);
            map.put("realPath",imgurlnew+newName);
//            map.put("relaPath",filePath);

        } catch (OSSException oe) {
            return ResultBeanObj.fail("路径错误");
        } catch (ClientException ce) {
            return ResultBeanObj.fail("路径错误");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ResultBeanObj.success().setData(map);
    }

    public static ResultBeanObj uploadTFile(MultipartFile file) throws IOException {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //文件扩展名
        String extName = fileName.substring(fileName.lastIndexOf("."));
        fileName =UUID.randomUUID().toString() +extName;
        Map map=new HashMap();
        String upath="Admin/YunQiUploadFiles/File"+fileName;
        String path =fileUrl+upath;
        //创建文件路径
        File dest = new File(path);
        try {
            //上传文件
            file.transferTo(dest); //保存文件
            map.put("imageUrl",upath);
            map.put("realPath",path);
        } catch (IOException e) {
            return ResultBeanObj.fail("路径错误");
        }
        return ResultBeanObj.success().setData(map);
    }

    /**
     * 创建模拟文件夹
     * @param ossClient oss连接
     * @param bucketName 存储空间
     * @param folder   模拟文件夹名如"qj_nanjing/"
     * @return  文件夹名
     */
    public  static String createFolder(OSSClient ossClient, String bucketName, String folder){
        //文件夹名
        final String keySuffixWithSlash =folder;
        //判断文件夹是否存在，不存在则创建
        if(!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)){
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            System.out.println("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir=object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    public static  ResultBeanObj upload(MultipartFile file){
        Map map=new HashMap();
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {

            //获取文件名
            String fileName = file.getOriginalFilename();
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            ObjectMetadata objectMetadata = getObjectMetadata(extName);
            if(objectMetadata == null){
                return ResultBeanObj.fail("暂不支持上传格式");
            }
            // 最后上传生成的文件名
            String finalFileName = UUID.randomUUID().toString() + extName;
            ossClient.putObject(bucketName, finalFileName, file.getInputStream(), objectMetadata);
            map.put("imageUrl","https://" + bucketName + "." + endpoint + "/" + finalFileName);
        } catch (OSSException oe) {
            return ResultBeanObj.fail("路径错误");
        } catch (ClientException ce) {
            return ResultBeanObj.fail("路径错误");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ResultBeanObj.success().setData(map);
    }

    public static ResultBeanObj uploadFileWithProgress(File f, HttpSession session, String fileName ){
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Map map=new HashMap();
        try {
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            ObjectMetadata objectMetadata = getObjectMetadata(extName);
            if(objectMetadata == null){
                return ResultBeanObj.fail("暂不支持上传格式");
            }
            String finalFileName = UUID.randomUUID().toString() + extName;
            // 上传 --> 带进度条上传
            ossClient.putObject(new PutObjectRequest(bucketName, finalFileName,f,objectMetadata).withProgressListener(new PutObjectProgressListener(session)));
            // 关闭client
            ossClient.shutdown();
            map.put("url","https://" + bucketName + "." + endpoint + "/" + finalFileName);
            map.put("key",finalFileName);
        } catch (Exception e) {
           return ResultBeanObj.fail(e.getMessage());
        }
        return ResultBeanObj.success().setData(map);
    }

    private static ObjectMetadata getObjectMetadata(String extName){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        List<String> errorExt = new ArrayList<>(Arrays.asList(".BAT", ".exe", ".bat", ".EXE"));
        if(errorExt.contains(extName)){
            return null;
        }
        if (extName.equals(".jpg") || extName.equals(".png") || extName.equals(".jpeg")) {
            objectMetadata.setContentType("image/jpg");
        }
        else if (extName.equals(".mp4")) {
            objectMetadata.setContentType("video/mp4");
        }
        else if (extName.equals(".pptx") || extName.equals(".ppt")) {
            objectMetadata.setContentType("application/vnd.ms-powerpoint")  ;
        }
        else if (extName.equals(".docx") || extName.equals(".doc")) {
            objectMetadata.setContentType("application/msword") ;
        }
        else if (extName.equals(".pdf")) {
            objectMetadata.setContentType("application/pdf");
        }
        else {
            return null;
        }
        return  objectMetadata;
    }




    /**
     * 获取指定视频的帧并保存为图片至指定目录
     * @param file  源视频文件路径
     * @throws Exception
     */
    public static ResultBeanObj fetchFrame(File file){
        Map map=new HashMap();
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file);
            ff.start();
            int lenght = ff.getLengthInFrames();
            int i = 0;
            Frame f = null;
            while (i < lenght) {
                // 过滤前5帧，避免出现全黑的图片，依自己情况而定
                f = ff.grabFrame();
                if ((i > 20) && (f.image != null)) {
                    break;
                }
                i++;
            }
            opencv_core.IplImage img = f.image;
            int width = img.width();
            int height = img.height();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img.getBufferedImage(), "jpg", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            ObjectMetadata objectMetadata = getObjectMetadata(".jpg");
            String finalFileName = UUID.randomUUID().toString() + ".jpg";
            ossClient.putObject(bucketName, finalFileName, input, objectMetadata);
            map.put("imageUrl","https://" + bucketName + "." + endpoint + "/" + finalFileName);
            map.put("width",width);
            map.put("height",height);
            ff.stop();
        }catch (Exception e){
            return  ResultBeanObj.fail("获取视频第五帧图片异常");
        }finally {
            ossClient.shutdown();
        }
        return ResultBeanObj.success().setData(map);

    }



}
