package com.temp.common.config;
import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class FileUploadConfig {

	@Value("${upload.file.size}")
	private String fileSize;
 
	@Bean
	public MultipartConfigElement configElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(fileSize); //文件大小限制
		//factory.setLocation("d:\\wmUpload\\");  //这个配置的话，路径一定要存在，不然会报错。
		return factory.createMultipartConfig();
	}

}
