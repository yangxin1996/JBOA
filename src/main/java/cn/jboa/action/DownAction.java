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
			fileName = new String("ͳ�Ʊ���.xls".getBytes(), "iso-8859-1");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//�ڶ���������excel���,��װ���ݵ�excel                    
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow row = sheet.createRow(0);//������һ��
		HSSFCell cell = row.createCell(0);//��һ��
		cell.setCellValue("�������б�"); //������
		HSSFCellStyle style = workbook.createCellStyle(); //������ʽ
		HSSFFont font = workbook.createFont(); //����������ʽ
		font.setFontHeight((short)(20*20)); //����
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //�Ӵ�
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //����
        style.setFont(font); //����������ʽ  
        cell.setCellStyle(style); //������ʽ
        sheet.addMergedRegion(new org.apache.poi.ss.util.Region(0,(short)0,0,(short)4)); //�ϲ���
        //String menus = "��Ʒ����,��Ʒ����,��Ʒ����";    
        row = sheet.createRow(1);//������һ��
        cell = row.createCell(0); //������һ��
        cell.setCellValue(new HSSFRichTextString("������ID"));
        cell = row.createCell(1);//�����ڶ���
        cell.setCellValue(new HSSFRichTextString("������"));      
        cell = row.createCell(2);//
        cell.setCellValue(new HSSFRichTextString("�¼�"));
        cell = row.createCell(3);//
        cell.setCellValue(new HSSFRichTextString("���"));        
        cell = row.createCell(4);//
        cell.setCellValue(new HSSFRichTextString("ʱ��"));  
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
			ClaimVoucher temp= list.get(i);
			row=sheet.createRow(i+2);//������i+1��                                   
			cell=row.createCell(0);//������һ��
			cell.setCellValue(temp.getId());               	
			cell=row.createCell(1);//�����ڶ���
			cell.setCellValue(temp.getCreator().getName());                     
			cell=row.createCell(2);
			cell.setCellValue(temp.getEvent());                
			cell=row.createCell(3);
			cell.setCellValue(temp.getTotalAccount());    
			cell=row.createCell(4);
			String dateString = formatter.format(currentTime);
			cell.setCellValue(dateString);    
			}
        //��������д�������
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
