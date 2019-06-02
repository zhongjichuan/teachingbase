package com.teachingbase.util;

import com.teachingbase.domain.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

public class ExcelUtil {

    public static String PATH = "E:/InerllijIdeaWorkspace/ManagementSystem of Out-of-School Teaching Base/student/attendance/";

    public static HSSFWorkbook generateExcel(String[] titles, List list, String sheetName,String teacherName){
        //总列数
        int length = 10;
        // 第一步，创建一个Excel文件
        HSSFWorkbook excel = new HSSFWorkbook();
        // 第二步，在Excel文件中添加一个sheet
        HSSFSheet sheet = excel.createSheet(sheetName);

        // Sheet样式
        HSSFCellStyle sheetStyle = excel.createCellStyle();
        // 设置列的样式
        for (int i = 0; i < length; i++) {
            // 设置列宽
            sheet.setColumnWidth(i, 3000);
            sheet.setDefaultColumnStyle((short) i, sheetStyle);
        }

        // 设置表头字体
        HSSFFont headfont = excel.createFont();
        headfont.setFontName("宋体");
        headfont.setFontHeightInPoints((short) 18);// 字体大小
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        // 表头样式
        HSSFCellStyle headstyle = excel.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);
        headstyle.setWrapText(true);// 自动换行

        // 设置列头字体
        HSSFFont columnHeadFont = excel.createFont();
        columnHeadFont.setFontName("黑体");
        columnHeadFont.setFontHeightInPoints((short) 12);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 列头样式
        HSSFCellStyle columnHeadStyle = excel.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        columnHeadStyle.setWrapText(true);
        columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色
        columnHeadStyle.setBorderTop((short) 1);// 边框的大小
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
        columnHeadStyle.setBorderRight((short) 1);// 边框的大小
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色

        // 设置普通单元格字体
        HSSFFont font = excel.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 10);
        // 普通单元格样式
        HSSFCellStyle style = excel.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
        style.setWrapText(true);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft((short) 1);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight((short) 1);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．
        // 第三步，在sheet中添加表头第1行
        HSSFRow row0 = sheet.createRow(0);
        // 设置行高
        row0.setHeight((short) 600);
        HSSFCell row0cell0 = row0.createCell(0);
        row0cell0.setCellValue(new HSSFRichTextString(sheetName));
        row0cell0.setCellStyle(headstyle);
        /**
         * 合并单元格
         *    第一个参数：第一个单元格的行数（从0开始）
         *    第二个参数：第二个单元格的行数（从0开始）
         *    第三个参数：第一个单元格的列数（从0开始）
         *    第四个参数：第二个单元格的列数（从0开始）
         */
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(region);
        //实训教师
        HSSFCell row0cell1 = row0.createCell(8);
        // 实训教师单元格
        HSSFFont font1 = excel.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 11);
        HSSFCellStyle style1 = excel.createCellStyle();
        style1.setFont(font1);
        style1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        row0cell1.setCellStyle(style1);
        row0cell1.setCellValue("实训教师：");

        HSSFCellStyle style2 = excel.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        HSSFCell row0cell2 = row0.createCell(length-1);
        row0cell2.setCellStyle(style1);
        row0cell2.setCellValue(teacherName);



        // 创建第二行
        HSSFRow row1 = sheet.createRow(1);
        //创建列头
        for (int i=0; i<titles.length; i++) {
            String title = titles[i];
            HSSFCell titleCell = row1.createCell(i);
            titleCell.setCellValue(title);
            titleCell.setCellStyle(columnHeadStyle);
        }

        //第四步，在sheet中添加内容
        if (list.size()!=0){
            for(int i=0; i<list.size(); i++){
                User user = (User) list.get(i);
                HSSFRow row = sheet.createRow(i+2);
                HSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(user.getUsername());
                cell0.setCellStyle(style);
                HSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(user.getName());
                cell1.setCellStyle(style);
            }
        }

        HSSFRow rowLast = sheet.createRow(list.size()+2);
        HSSFCell lastCell = rowLast.createCell(0);
        HSSFCellStyle styleLast = excel.createCellStyle();
        HSSFFont lastFont = excel.createFont();
        lastFont.setColor(HSSFColor.RED.index);
        lastFont.setFontName("宋体");
        lastFont.setFontHeightInPoints((short) 10);
        styleLast.setFont(lastFont);
        lastCell.setCellStyle(styleLast);
        lastCell.setCellValue("* 日期格式如下：2.01；考勤类型：早退，迟到，正常，旷工");
        return excel;
    }
}
