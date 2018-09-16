package cn.jboa.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jboa.entity.ClaimVoucher;
import cn.jboa.service.ClaimVoucherStatisticsService;
import cn.jboa.service.ClaimVouyearStatisticsService;

@Controller("downAction")
@Scope("prototype")
public class DownAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="claimVoucherStatisticsService")
	private ClaimVoucherStatisticsService claimVoucherStatisticsService;
	@Resource(name="claimVouyearStatisticsService")
	private ClaimVouyearStatisticsService claimVouyearStatisticsService;	

	private int year;
	private int selectMonth;
	private int departmentId;
	private String fileName;
	private ByteArrayInputStream is;



	public String getFileName() {
		return fileName;
	}

	public ByteArrayInputStream getIs() {
		return is;
	}

	public void setIs(ByteArrayInputStream is) {
		this.is = is;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSelectMonth() {
		return selectMonth;
	}

	public void setSelectMonth(int selectMonth) {
		this.selectMonth = selectMonth;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
    @SuppressWarnings("deprecation")
	public String execute() throws Exception{
    	if(getSessionEmployee()!=null&&getSessionEmployee().getSysPosition()!=null&&getSessionEmployee().getSysPosition().getId()>1){
 
    	}else{
    		return ERROR;
    	}
    	
		List<ClaimVoucher> list = null;
		try {
			if(selectMonth>0){
				list = claimVoucherStatisticsService.findDeptStatisticsDetailByMonth(year,selectMonth,departmentId);
			}else{
				list = claimVouyearStatisticsService.findDeptStatisticsDetailByYear(year, departmentId);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileName = new String("统计报表.xls".getBytes(), "iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//第二步：构建excel表格,封装数据到excel                    
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow row = sheet.createRow(0);//创建第一行
		HSSFCell cell = row.createCell(0);//第一列
		cell.setCellValue("报销单列表"); //行内容
		HSSFCellStyle style = workbook.createCellStyle(); //创建样式
		HSSFFont font = workbook.createFont(); //创建字体样式
		font.setFontHeight((short)(20*20)); //字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //加粗
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
        style.setFont(font); //设置字体样式  
        cell.setCellStyle(style); //设置样式
        sheet.addMergedRegion(new org.apache.poi.ss.util.Region(0,(short)0,0,(short)4)); //合并列
        //String menus = "产品名称,产品类型,产品描述";    
        row = sheet.createRow(1);//创建第一行
        cell = row.createCell(0); //创建第一列
        cell.setCellValue(new HSSFRichTextString("报销单ID"));
        cell = row.createCell(1);//创建第二列
        cell.setCellValue(new HSSFRichTextString("报销人"));      
        cell = row.createCell(2);//
        cell.setCellValue(new HSSFRichTextString("事件"));
        cell = row.createCell(3);//
        cell.setCellValue(new HSSFRichTextString("金额"));        
        cell = row.createCell(4);//
        cell.setCellValue(new HSSFRichTextString("时间"));  
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
			ClaimVoucher temp= list.get(i);
			row=sheet.createRow(i+2);//创建第i+1行                                   
			cell=row.createCell(0);//创建第一列
			cell.setCellValue(temp.getId());               	
			cell=row.createCell(1);//创建第二列
			cell.setCellValue(temp.getCreator().getName());                     
			cell=row.createCell(2);
			cell.setCellValue(temp.getEvent());                
			cell=row.createCell(3);
			cell.setCellValue(temp.getTotalAccount());    
			cell=row.createCell(4);
			String dateString = formatter.format(currentTime);
			cell.setCellValue(dateString);    
			}
        //第三步：写入输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			workbook.write(baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] ba = baos.toByteArray(); 
        is = new ByteArrayInputStream(ba); 
        return SUCCESS;    		
    }
}
