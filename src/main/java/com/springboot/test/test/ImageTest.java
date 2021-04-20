package com.springboot.test.test;

import java.util.Arrays;

import javax.imageio.ImageIO;

public class ImageTest {
	public static void main(String args[]) {
		String readFormats[] = ImageIO.getReaderFormatNames();
		String writeFormats[] = ImageIO.getWriterFormatNames();
		System.out.println("Readers: : " + Arrays.toString(readFormats));
		System.out.println("Writers: : " + Arrays.toString(writeFormats));
	}
}
