package com.springboot.test.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.IIOParamController;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);
	
	public static void changeImageFormat(String sourcePath, String targetPath,String name) {
		
		File source = new File(sourcePath);
		File target= new File(targetPath + name);
		try {
			BufferedImage bi = ImageIO.read(source);
			ImageIO.write(bi, "png", target);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
//		changeImageFormat("C:\\Users\\Administrator\\Desktop\\test\\sample.jpg","C:\\Users\\Administrator\\Desktop\\test\\","sample-convertor-bpm.bpm");
//		changeImageFormatFromFile("C:\\Users\\Administrator\\Desktop\\test\\sample-tiff.tiff","C:\\Users\\Administrator\\Desktop\\test\\","sample-convertor-tiff-0.5-insert.tiff");
		getReaderFormatNames();
	}
	
    public static void changeImageFormatFromFile(String sourcePath, String targetPath,String name) {
		
		File source = new File(sourcePath);
		File target= new File(targetPath + name);
		
		try {
			
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("tiff");
			ImageReader reader = readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(source);
			reader.setInput(iis,true);
			
			ImageReadParam readParam = reader.getDefaultReadParam();
			int imageIndex = 0;
			int half_width = reader.getHeight(imageIndex)/2;
			int half_height = reader.getHeight(imageIndex)/2;
			Rectangle rect = new Rectangle(0, 0, half_width, half_height); 
			readParam.setSourceRegion(rect);
			
			IIOParamController controller = readParam.getController();
			if (controller != null) {
			        controller.activate(readParam);
			}
			
		    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("tiff");
		    ImageWriter writer = writers.next();

		    ImageOutputStream ios = ImageIO.createImageOutputStream(target);
		    writer.setOutput(ios);

		    BufferedImage first_bi = reader.read(0);
		    IIOImage first_IIOImage = new IIOImage(first_bi, null, null);
		    writer.write(null, first_IIOImage, null);
		    for(int i =1 ; i<5; i++) {
		    	if (writer.canInsertImage(i)) {
		    		BufferedImage second_bi = reader.read(i, readParam);
		    		IIOImage second_IIOImage = new IIOImage(second_bi, null, null);
		            writer.writeInsert(i, second_IIOImage, null);
				    } else {
				        logger.error("Writer can't append a second image!");
				    }
		    }
		    

		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
    public static String getReaderFormatNames() {
        String readFormats[] = ImageIO.getReaderFormatNames();
        String writeFormats[] = ImageIO.getWriterFormatNames();
        logger.info("Readers: : " + Arrays.toString(readFormats));
        logger.info("Writers: : " + Arrays.toString(writeFormats));
        return Arrays.toString(readFormats);
    }
}
