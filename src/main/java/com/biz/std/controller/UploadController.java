package com.biz.std.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.aliyun.oss.OSSClient;
import com.biz.std.model.StudentImage;
import com.biz.std.service.UploadService;


@Controller
@RequestMapping("/")
public class UploadController {

	@Autowired
	private UploadService uploadService;
	/**
	 * 上传图片
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/uploadimage.do")
	public void uploadImage(HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		// 客户端网页控制为UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/heml;charset=utf-8");

		// 在解析请求之前先判断请求类型是否为文件上传类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 创建文件解析对象
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建上传文件实例
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		// 打印信息
		String message = "444";
		List<?> list = null;
		try {
			// 解析request为fileitem，因为支持多文件上传
			list = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		// 进行迭代
		Iterator<?> it = list.iterator();
		StudentImage si = null;
		while (it.hasNext()) {
			FileItem item = (FileItem) it.next();
			//得到文本信息
            if (item.isFormField()) {
            	if("id".equals(item.getFieldName())){
            		 si = new StudentImage();
            		 String fileName = item.getString("utf-8");
            		 si.setStudentid(Integer.parseInt(fileName));
            	}  
            } else {
            	// 获取文件名
    			String fileName = item.getName();
    			if (StringUtils.isNotEmpty(fileName)) {
    				// 获得最后一个/的位置
    				int index = fileName.lastIndexOf("/") + 1;
    				// 获得真实文件名
    				String relFileName = fileName.substring(index);
    				//建立一个服务器文件名
    				//String studentName = UUID.randomUUID().toString();
    				
    				//查询数据库中是否已经上传了
    				StudentImage stuImg = uploadService.findOneStudentImage(si.getStudentid());
    				if(stuImg != null){
    					//删除数据库文件
    					uploadService.deleteStudentImage(stuImg);
    				}
    				
    				// 获得文件输入流
    				InputStream is = item.getInputStream();
    				// endpoint是深圳
    				String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
    				// 云账号AccessKey
    				String accessKeyId = "LTAIpaLT0jc5UQie";
    				String accessKeySecret = "ZFUVLUDvCEJJkMbZXAOGgfpnOgvyv0";
    				// 创建OSSClient实例
    				OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    				ossClient.putObject("hwhyj", fileName, is);

    				// 获得url
    				// 设置URL过期时间为10年 3600l* 1000*24*365*10
    				Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
    				URL url = ossClient.generatePresignedUrl("hwhyj", fileName, expiration);

    				// 关闭client
    				ossClient.shutdown();
    				is.close();
    				message = url.toString();
 
    				si.setFilename(relFileName);
    				si.setOsspath(url.toString());
    				uploadService.saveImage(si);
    			}
            }
	        
			
		}
		response.getWriter().println(message);
	}
	
			
}
