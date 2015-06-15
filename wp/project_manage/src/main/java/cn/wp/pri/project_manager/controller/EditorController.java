package cn.wp.pri.project_manager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.core.commons.basic.file.entity.File;
import cn.core.commons.basic.file.service.FileService;
import cn.core.commons.framework.support.controller.BaseController;
import cn.yjhl.commons.tools.json.JSONTools;

@Controller("pm.editorController")
@RequestMapping("editor")
public class EditorController extends BaseController {
	@Autowired FileService fileService;
	//#-- 文件上传接口
	@RequestMapping("upload.do")
	@ResponseBody
	public Object upload(
			@RequestParam("imgFile")MultipartFile file){
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		String imgPath=null;
		File oFileEntity = new File();
		oFileEntity.setName(file.getOriginalFilename());
		oFileEntity.setSize(file.getSize());
		oFileEntity.setType(file.getContentType());
		oFileEntity.setCode("edit");
		try {
			oFileEntity.setInputStream(file.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			java.io.File f = fileService.createFile(oFileEntity);
			imgPath = f.getPath().replace(getRealPath(), getBasePath()+"/").replace(java.io.File.separator, "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		jsonMap.put("error", 0);
		jsonMap.put("url", imgPath);
		return JSONTools.toJSON(jsonMap);
	}

}