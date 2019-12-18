package com.deo.stark.sys.upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.deo.stark.base.vo.BasicResponseVO;
import com.deo.stark.sys.upload.service.UploadService;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@ResponseBody
	@RequestMapping("/doUpload")
	public BasicResponseVO<?> doUpload(@RequestParam("file") MultipartFile file, @RequestParam String type, HttpServletRequest request) throws IOException {
		
		BasicResponseVO<?> result = null;
		
		// absolutely file path
		String contextPath = request.getSession().getServletContext().getRealPath("/upload");
		
		// original file name
		String originalFilename = file.getOriginalFilename();

		// new file name
		String[] split = originalFilename.split("\\.");
		String suffix = split[split.length - 1];
		String newFileName = UUID.randomUUID().toString() + "." + suffix;
		
		// upload file extra message
		// String name = file.getName();
		String contentType = file.getContentType();
		// long size = file.getSize();
		
		
		try {
			
			// save file to local
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(contextPath + "/" + newFileName));

			// save data to db
			String dataId = uploadService.add(originalFilename, newFileName, contentType);
			
			Map<String, Object> data = new HashMap<String, Object>();
			
			// data.put("viewPath", fileURL);
			data.put("id", dataId);
			result = new BasicResponseVO<>(true, "上传成功", data);
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, "上传失败：" + e.getMessage());
		}
		
		return result;
	}
}
