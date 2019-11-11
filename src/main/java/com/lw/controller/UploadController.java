package com.lw.controller;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lw.common.constant.ErrorCode;
import com.lw.common.exception.BaseException;
import com.lw.common.page.ResultWrapper;
import com.lw.common.utils.FileUtils;

import cn.hutool.http.HttpUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/upload")
public class UploadController {
	
	@ApiOperation("视频/图片上传")
	@PostMapping("")
	public ResultWrapper<Map<?,?>> uploadVideo(@ApiParam(value = "上传文件", required = true) MultipartFile file,
			HttpServletRequest request) {
		File f = new File(file.getOriginalFilename());
		Map<?, ?> rsMap = new HashMap<String, Object>();
		try {
			InputStream ins = file.getInputStream();
			FileUtils.inputStreamToFile(ins, f);
			//文件上传,fastdfs测试服务器
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//文件
			paramMap.put("file", f);
	        //输出
	        paramMap.put("output", "json");
	        //场景
	        paramMap.put("scene", "diandi");
	        String rs = HttpUtil.post("http://114.55.102.98:8080/upload", paramMap);
	        rsMap = JSONObject.parseObject(rs, HashMap.class);
		} catch (Exception e) {
			throw new BaseException(ErrorCode.FILE_UPLOAD_FAILED);
			// 无论成功与否，最后都删除这个临时文件
		} finally {
			File del = new File(f.toURI());
			del.delete();
		}
        return ResultWrapper.success(rsMap);
	}
	
	
}
