package com.example.spring02.controller.upload;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring02.util.UploadFileUtils;

@Controller
public class AjaxUploadController {
	private static final Logger logger =
			LoggerFactory.getLogger(AjaxUploadController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
	public String uploadAjax() {
		return "/upload/uploadAjax";
	}
	
	@ResponseBody
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST,
			produces="text/plain;charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		return new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),
				file.getBytes()),HttpStatus.OK);
	}
}
