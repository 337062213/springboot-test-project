 package com.springboot.test.util.pdf;
 import com.itextpdf.text.*;
 import com.itextpdf.text.pdf.BaseFont;
 import com.itextpdf.text.pdf.PdfPCell;
 import com.itextpdf.text.pdf.PdfPTable;
 import com.itextpdf.text.pdf.PdfWriter;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;

 /**
  * Created on 2017/8/15
  * Author: youxingyang.
  */
 public class A14 {
     public static String split = "<brb>";
     public static void main(String[] args) {
         Document document = new Document(PageSize.A4, 48, 48, 60, 65);

         try {
             PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\EDZ\\Desktop\\test\\pdf\\可以重复合并行A14.pdf"));
             document.open();
             // 设置字体
             BaseFont bfCN = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
             // 正文的字体
             Font headFont = new Font(bfCN, 12f, Font.BOLD, BaseColor.ORANGE);
             Font textFont = new Font(bfCN, 12f, Font.NORMAL, BaseColor.BLUE);

             //添加测试数据
             List<String[]> list = new ArrayList<>();
             for (int i = 0; i < 24; i++) {
                 String[] arr = new String[4];
                 String value;
                 if (i < 4) {
                     value = "A";
                 } else if (i >= 4 && i < 6) {
                     value = "B";
                 } else if (i >= 6 && i < 8) {
                     value = "C";
                 } else if (i >= 8 && i < 13) {
                     value = "D";
                 } else if (i == 13 || i == 14) {
                     value = "A";
                 } else if (i > 14 && i < 16) {
                     value = "E";
                 } else if (i == 17) {
                     value = "F";
                 } else {
                     value = "G";
                 }
                 arr[0] = "0" + i;
                 arr[1] = value;
                 arr[2] = "2" + i;
                 arr[3] = "3" + i;
                 list.add(arr);
             }

             //复制要合并的列
             List<String> ListIn = new ArrayList<>();
             for (String[] aList : list) {
                 ListIn.add(aList[1]);
             }
             //根据算法改变合并列的值
             List<String> changeList = changeList(ListIn);

             //改变该列后把它复制到原来的list
             for (int i = 0; i < changeList.size(); i++) {
                 String[] arr = list.get(i);
                 arr[1] = changeList.get(i);
                 list.set(i, arr);
             }

             document.newPage();
             //建立一个4列的表格
             PdfPTable table = new PdfPTable(4);
             String[] titleArr = {"第1列", "合并列", "第3列", "第4列"};
             //加表格头部
             addTitle(table, titleArr, headFont);
             //加表格内容
             addContent(table, list, textFont);
             document.add(table);
             document.close();
         } catch (DocumentException | IOException e){
             e.printStackTrace();
         }
     }

     private static void addContent(PdfPTable table, List<String[]> list, Font textFont) {
         //表格数据内容
         for (String[] str : list) {
             for (int j = 0; j < str.length; j++) {
                 String value = str[j];
                 if (value != null) {
                     Paragraph paragraph01;
                     int spanNum = 1;
                     if (j == 1 && value.contains(split)) {
                         spanNum = Integer.parseInt(value.split(split)[1]);
                         paragraph01 = new Paragraph(value.split(split)[0], textFont);
                     } else {
                         paragraph01 = new Paragraph(value, textFont);
                     }
                     paragraph01.setAlignment(1);
                     PdfPCell cell = new PdfPCell();
                     cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                     cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);//然并卵
                     cell.setPaddingTop(-2f);//把字垂直居中
                     cell.setPaddingBottom(8f);//把字垂直居中
                     cell.addElement(paragraph01);
                     cell.setRowspan(spanNum);
                     table.addCell(cell);
                 }
             }
         }
     }

     private static void addTitle(PdfPTable table, String[] titleArr, Font headFont) {
         for (String aTitleArr : titleArr) {
             Paragraph p = new Paragraph(aTitleArr, headFont);
             PdfPCell cell = new PdfPCell();
             p.setAlignment(1);
             cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
             cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);//然并卵
             cell.setPaddingTop(-2f);//把字垂直居中
             cell.setPaddingBottom(8f);//把字垂直居中
             cell.addElement(p);
             table.addCell(cell);
         }
     }

     /**
      * 改变合并列的值
      * @param drugList 内容支持 'A,A,B,C,D,D,D,E' 支持 'A,A,B,C,A,D,D,D,E'
      * @return
      */
     private static List<String> changeList(List<String> drugList) {
         List<String> drugListCopy = new ArrayList<String>();
         drugListCopy.addAll(drugList);

         int nullNum = 0;
         for (int i = 0; i < drugList.size(); i++) {
             if (i > 0) {
                 if (drugList.get(i).equals(drugList.get(i - 1))) {
                     drugListCopy.set(i, null);
                     nullNum++;
                 } else {
                     if (nullNum > 0) {
                         int start = i - nullNum - 1;
                         drugListCopy.set(start, drugList.get(start) + split + (nullNum + 1));
                         nullNum = 0;
                     }
                 }

                 // 处理某一列值都相同的情况
                 if (nullNum > 0) {
                     int start = i - nullNum;
                     drugListCopy.set(start, drugList.get(start) + split + (nullNum + 1));
                 }
             }
         }
         return drugListCopy;
     }
 }