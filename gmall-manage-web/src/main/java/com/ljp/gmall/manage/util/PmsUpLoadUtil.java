package com.ljp.gmall.manage.util;

import com.ljp.gmall.manage.constant.UploadConstant;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUpLoadUtil {

    public static String uploadImage(MultipartFile multipartFile){
        String ImagUrl = UploadConstant.UploadURL;
        //TODO 文件读取
        String tracker = PmsUpLoadUtil.class.getResource("/tracker.conf").getPath();
        try {
            ClientGlobal.init(tracker);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            StorageClient storageClient = new StorageClient(trackerServer,null);

            String originalFilename = multipartFile.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);


            String[] uploadInfos= storageClient.upload_file(multipartFile.getBytes(),extName,null);

            for (String uploadinfo :uploadInfos) {
                ImagUrl += "/" + uploadinfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return ImagUrl;
    }
}
