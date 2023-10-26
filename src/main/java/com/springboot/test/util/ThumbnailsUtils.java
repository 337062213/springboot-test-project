package com.springboot.test.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ThumbnailsUtils {
    public static void main(String[] a) throws IOException {
        /*
         * 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变
         * 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of("C:/Users/EDZ/Desktop/test-material/image/png/捕获.PNG")
                .size(200, 300)
                .toFile("C:/Users/EDZ/Desktop/png1.PNG");
        //scale(比例)
        Thumbnails.of("C:/Users/EDZ/Desktop/test-material/image/png/捕获.PNG")
                .scale(0.25f)
                .toFile("C:/Users/EDZ/Desktop/png2.PNG");
        //rotate(角度),正数：顺时针负数：逆时针
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280,1024)
                .rotate(90)
                .toFile("c:/a380_rotate+90.jpg");
        //watermark(位置，水印图，透明度)
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280,1024)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")),0.5f)
                .outputQuality(0.8f)
                .toFile("c:/a380_watermark_bottom_right.jpg");
        //图片中心400*400的区域
        Thumbnails.of("images/a380_1280x1024.jpg")
                .sourceRegion(Positions.CENTER,400,400)
                .size(200,200)
                .keepAspectRatio(false)
                .toFile("c:/a380_region_center.jpg");

        //图片右下400*400的区域
        Thumbnails.of("images/a380_1280x1024.jpg")
                .sourceRegion(Positions.BOTTOM_RIGHT,400,400)
                .size(200,200)
                .keepAspectRatio(false)
                .toFile("c:/a380_region_bootom_right.jpg");

        //指定坐标
        Thumbnails.of("images/a380_1280x1024.jpg")
                .sourceRegion(600,500,400,400)
                .size(200,200)
                .keepAspectRatio(false)
                .toFile("c:/a380_region_coord.jpg");
        //outputFormat(图像格式)
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280,1024)
                .outputFormat("png")
                .toFile("c:/a380_1280x1024.png");

        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280,1024)
                .outputFormat("gif")
                .toFile("c:/a380_1280x1024.gif");
        //toOutputStream(流对象)
        OutputStream os=new FileOutputStream("c:/a380_1280x1024_OutputStream.png");
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280,1024)
                .toOutputStream(os);
        //asBufferedImage()返回BufferedImage
        BufferedImage thumbnail=Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280,1024)
                .asBufferedImage();
        ImageIO.write(thumbnail,"jpg",new File("c:/a380_1280x1024_BufferedImage.jpg"));
    }

    public static  void formate() throws IOException {
        File originalImage = new File("C:/Users/EDZ/Desktop/test-material/image/webp/");
        String thumbnailImageName=originalImage.getName(); //缩略图输出名
        String thumbnailImagePath; //缩略图输出地址
        String format = thumbnailImageName.substring(thumbnailImageName.lastIndexOf(".")).toUpperCase();
        String fileName = thumbnailImageName.substring(0, thumbnailImageName.lastIndexOf(".")).toUpperCase();
        String thumbnailImageFile = "C:\\Users\\EDZ\\Desktop\\test\\" + "thumb_" +thumbnailImageName;
        switch (format){
            case "JPG":
                thumbnailImagePath=System.getProperty("user.dir") + "/file/Output/"
                        + fileName+".jpg";
                Thumbnails.of(originalImage).scale(0.25)
                        .outputFormat("jpg")
                        .outputQuality(0.25)
                        .toFile(thumbnailImageFile);
                break;
            case "PNG":
                thumbnailImagePath=System.getProperty("user.dir") + "/file/Output/"
                        + fileName+".png";
                break;
            case "WEBP":
                thumbnailImagePath=System.getProperty("user.dir") + "/file/Output/"
                        + fileName+".webp";
                break;
            case "BMP":
                thumbnailImagePath=System.getProperty("user.dir") + "/file/Output/"
                        + fileName+".bmp";
                break;
            default:
                thumbnailImagePath=System.getProperty("user.dir") + "/file/Output/" + thumbnailImageName;
                break;
        }
    }
}
