package edu.lsnu.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.PageBean;
import edu.lsnu.utils.ExcelUtil;
import edu.lsnu.utils.TrainingDateUtil;

/**
 * 统计分析
 * 
 * @author liangsu
 */
@Controller
@Scope("prototype")
public class StatisticalAction extends BaseAction<Object> {

	private int grade;
	private InputStream excelStream;
	private String fileName;

	/**
	 * 统计去各个基地的学生人数（基地人数统计）
	 * 
	 * @return
	 */
	public String basePsnNumUI() {
		if (grade <= 0) {
			grade = TrainingDateUtil.getTrainingDate().getGrade();
		}
		List<Map<String, Object>> list = statisticalService.basePsnNum(grade);
		putContext("list", list);
		return "basePsnNumUI";
	}

	/**
	 * 统计去各个基地的学生表
	 * 
	 * @return
	 */
	public String baseStudentUI() {
		if (grade <= 0) {
			grade = TrainingDateUtil.getTrainingDate().getGrade();
		}
		PageBean pageBean = statisticalService.baseStudent(currentPage, pageSize, grade);
		putContext("pageBean", pageBean);
		return "baseStudentUI";
	}

	/**
	 * 统计去各个基地的学生人数（基地人数统计）
	 */
	public String basePsnNumExcel() throws Exception{
		//1.获取数据
		if (grade <= 0) {
			grade = TrainingDateUtil.getTrainingDate().getGrade();
		}
		List<Map<String, Object>> list = statisticalService.basePsnNum(grade);
		
		//2.生成excel
		String[] headers = new String[]{"基地名称","前往实习人数","学生评价总分","学生评价平均分","学生人数评价总分","基地综合评价总分"};
		String[] keys = new String[]{"name","psnNum","totalScore","avgScore","numScore","score"};
		excelStream = ExcelUtil.exportExcel("基地人数统计表", headers, keys, list);
		
		//3.设置文件名
		fileName = grade+"年基地人数统计表.xls";
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		
		return "excel";
	}
	
	/**
	 * 统计去各个基地的学生表
	 * 
	 * @return
	 */
	public String baseStudentExcel() throws Exception{
		//1.获取数据
		if (grade <= 0) {
			grade = TrainingDateUtil.getTrainingDate().getGrade();
		}
		List<Map<String,Object>> data = statisticalService.baseStudent(grade);
		
		//2.生成excel
		String[] headers = new String[]{"学号","姓名","班级","住址","电话","实习基地","实习地址"};
		String[] keys = new String[]{"id","username","classname","address","phone","tName","tAddress"};
		excelStream = ExcelUtil.exportExcel("基地学生统计表", headers, keys, data);
		
		//3.设置文件名
		fileName = grade+"年基地学生统计表.xls";
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		
		return "excel";
	}

	// ---
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
