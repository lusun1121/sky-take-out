package com.sky.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sky.constant.LocalUploadConstant;

@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {
	

//    private String localDirectory = LocalUploadConstant.LOCAL_UPLOAD_DIR;

    /**
     * 文件上传到本地
     *
     * @param bytes
     * @param objectName
     * @return
     */
	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
    
	public String upload(byte[] bytes, String objectName) {
		if (LocalUploadConstant.LOCAL_UPLOAD_DIR != null) {
	
	        // Define the file path
	        String filePath = LocalUploadConstant.LOCAL_UPLOAD_DIR + "/" + objectName;
	
	        // Create a file object
	        File file = new File(filePath);
	
	        try (FileOutputStream fos = new FileOutputStream(file)) {
	            // Write the bytes to the file
	            fos.write(bytes);
	            fos.flush();
	        } catch (IOException e) {
	            log.error("File upload failed", e);
	            return null;
	        }
	
	        // Log the file path where the file was saved
	        log.info("File uploaded to: {}", filePath);
	
	        return filePath;
		} else {
			log.info("Error in definition of local path to save uploaded image");
			return null;
		}
    }
}
//package com.sky.utils;
//
//import com.aliyun.oss.ClientException;
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import com.aliyun.oss.OSSException;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import java.io.ByteArrayInputStream;
//
//@Data
//@AllArgsConstructor
//@Slf4j
//public class AliOssUtil {
//
//    private String endpoint;
//    private String accessKeyId;
//    private String accessKeySecret;
//    private String bucketName;
//
//    /**
//     * 文件上传
//     *
//     * @param bytes
//     * @param objectName
//     * @return
//     */
//    public String upload(byte[] bytes, String objectName) {
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        try {
//            // 创建PutObject请求。
//            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//
//        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
//        StringBuilder stringBuilder = new StringBuilder("https://");
//        stringBuilder
//                .append(bucketName)
//                .append(".")
//                .append(endpoint)
//                .append("/")
//                .append(objectName);
//
//        log.info("文件上传到:{}", stringBuilder.toString());
//
//        return stringBuilder.toString();
//    }
//}
