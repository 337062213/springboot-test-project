package com.springboot.test.util.qr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QrCodeUtil {
     private static Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);
     private static final int BLACK = 0xFF000000;
     private static final int WHITE = 0xFFFFFFFF;
     public static void main(String[] args) {
         //  要生成二维码的链接
         String url = "https://www.baidu.com";
         //  指定路径：D:\User\Desktop\testQrcode
         String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode";
         //  指定二维码图片名字
         String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
         createQrCode(url, path, fileName);
     }

     public static String createQrCode(String url, String path, String fileName) {
         try {
             Map<EncodeHintType, String> hints = new HashMap<>();
             hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
             BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 400, 400, hints);
             File file = new File(path, fileName);
             if (file.exists() || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) && file.createNewFile())) {
                 writeToFile(bitMatrix, "jpg", file);
                 logger.info("搞定：" + file);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }

     static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
         BufferedImage image = toBufferedImage(matrix);
         if (!ImageIO.write(image, format, file)) {
             throw new IOException("Could not write an image of format " + format + " to " + file);
         }
     }

     static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
         BufferedImage image = toBufferedImage(matrix);
         if (!ImageIO.write(image, format, stream)) {
             throw new IOException("Could not write an image of format " + format);
         }
     }

     private static BufferedImage toBufferedImage(BitMatrix matrix) {
         int width = matrix.getWidth();
         int height = matrix.getHeight();
         BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
                 image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
             }
         }
         return image;
     }
 }

