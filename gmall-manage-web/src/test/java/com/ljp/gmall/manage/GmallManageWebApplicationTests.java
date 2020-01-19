package com.ljp.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {


    @Test
    public void upload(){

        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();
        System.out.println(tracker+"hahahaha");
        try {
            ClientGlobal.init(tracker);
        }catch (Exception e){
            e.printStackTrace();
        }
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer =null;
        try {
            trackerServer = trackerClient.getTrackerServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StorageClient storageClient = new StorageClient(trackerServer,null);
        String[] uploadInfos = null;
        try {
             uploadInfos= storageClient.upload_file("F:\\新建文件夹\\1-121009105G0.jpg","jpg",null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        String ImagUrl = null;
        for (String uploadinfo :uploadInfos
             ) {
            ImagUrl += "/" + uploadinfo;
        }
        System.out.println(ImagUrl);
    }
    @Test
    public void contextLoads() {
    }

}
