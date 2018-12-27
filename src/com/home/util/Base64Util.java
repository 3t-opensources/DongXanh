package com.home.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {

	public static String encodeToString(BufferedImage image, String type) throws Exception{
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return imageString;
	}
	
	public static BufferedImage decodeToImage(String imageString) throws Exception {
		
		
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return image;
    }
	
	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
