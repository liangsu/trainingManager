package edu.lsnu.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.mchange.io.FileUtils;

public class UploadAction extends BaseAction{

	private File file;
	private String fileFileName;
	private String fileType;
	
	public void upload(){
		//1.图片保存路径
		String path = "upload"+File.separator;
		String webRootPath = ServletActionContext.getServletContext().getRealPath("/");
		
		//2.新文件名
		String fileType = fileFileName.substring(fileFileName.indexOf('.') + 1);
		String newFileName = System.currentTimeMillis() + "." + fileType;
		
		//3.创建文件夹
		File newFile = new File(webRootPath + path + newFileName);
		if(!newFile.getParentFile().exists()){
			newFile.getParentFile().mkdirs();
		}
		
		try {
			//4.保存图片
			FileInputStream is = new FileInputStream(file);
			FileOutputStream os = new FileOutputStream(newFile);
			FileChannel isChannel = is.getChannel();
			FileChannel osChannel = os.getChannel();
			isChannel.transferTo(0, isChannel.size(), osChannel);
			isChannel.close();
			osChannel.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//5.返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", path + newFileName);
		printJson(map);
	}

	// ---
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
