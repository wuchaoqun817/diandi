package com.lw.common.file.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@Configuration
public class MultipartConfig {

	private long maxFileSize = 300;

	private long maxRequestSize = 320;

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个文件最大
		factory.setMaxFileSize(DataSize.of(maxFileSize, DataUnit.MEGABYTES));
		/// 设置总上传数据总大小
		factory.setMaxRequestSize(DataSize.of(maxRequestSize, DataUnit.MEGABYTES));
		return factory.createMultipartConfig();
	}
}
