package com.home.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
 
/**
 * Hash functions utility class.
 * @author www.codejava.net
 *
 */
public class HashGeneratorUtils {
    
 
    public static String generateMD5(String message) throws Exception {
        return hashString(message, "MD5");
    }
 
    public static String generateSHA1(String message) throws Exception {
        return hashString(message, "SHA-1");
    }
 
    public static String generateSHA256(String message) throws Exception {
        return hashString(message, "SHA-256");
    }
 
    private static String hashString(String message, String algorithm)
            throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
 
            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new Exception(
                    "Could not generate hash from String", ex);
        }
    }
 
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
    
    private static String hashFile(File file, String algorithm)
            throws Exception {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
     
            byte[] bytesBuffer = new byte[1024];
            int bytesRead = -1;
     
            while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
                digest.update(bytesBuffer, 0, bytesRead);
            }
     
            byte[] hashedBytes = digest.digest();
     
            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | IOException ex) {
            throw new Exception(
                    "Could not generate hash from file", ex);
        }
    }
    
	private static String hashListFile(List<File> list_file, String algorithm)
            throws Exception {
        try{
    		  FileInputStream inputStream = null;
    		  MessageDigest digest = MessageDigest.getInstance(algorithm);
    		  byte[] bytesBuffer = new byte[1024];
    		  int bytesRead = -1;
	          for(int i = 0; i < list_file.size(); i ++){
	        	  inputStream = new FileInputStream(list_file.get(i));
	        	  while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
	                  digest.update(bytesBuffer, 0, bytesRead);
	              }
	        	  inputStream.close();
	          }
            byte[] hashedBytes = digest.digest();
            return convertByteArrayToHexString(hashedBytes);
        } catch (Exception ex) {
            throw new Exception(
                    "Could not generate hash from file", ex);
        }
    }
    
    public static String generateMD5(File file) throws Exception {
        return hashFile(file, "MD5");
    }
     
    public static String generateMD5ListFile(List<File> file) throws Exception {
        return hashListFile(file, "MD5");
    }
    public static String generateSHA1(File file) throws Exception {
        return hashFile(file, "SHA-1");
    }
     
    public static String generateSHA256(File file) throws Exception {
        return hashFile(file, "SHA-256");
    }
    
    
    public static void main(String[] args) {
        try {
//        	System.out.println(generateMD5("3.CC_Primary_Pre_20170630.tif"));
//        	System.out.println(generateSHA1("Tran Thien Toan"));
//        	System.out.println(generateSHA256("Tran Thien Toan"));
        	
//            String filePath = "/home/tttoan/Desktop/TEST/AO_Fischer/I0036074.zip";
//            System.out.println("File Path: " + filePath);
//            File file = new File(filePath);
////             
//            String md5Hash = HashGeneratorUtils.generateMD5(file);
//            System.out.println("MD5 Hash: " + md5Hash);
//        	List<File> listFile = new ArrayList<File>();
//        	listFile.add(new File("/home/emcvn/0/1.pdf"));
//        	listFile.add(new File("/home/emcvn/0/2.pdf"));
//        	listFile.add(new File("/home/emcvn/0/3.pdf"));
//        	System.out.println("MD5 = " + generateMD5ListFile(listFile));
//             
//            String sha1Hash = HashGeneratorUtils.generateSHA1(file);
//            System.out.println("SHA-1 Hash: " + sha1Hash);
// 
//            String sha256Hash = HashGeneratorUtils.generateSHA256(file);
//            System.out.println("SHA-256 Hash: " + sha256Hash);     
        	File a = new File("/mnt/production/Application/Log_SDD/cvnem/DataViettinAviva/DataViettinAviva_0004.pdf");
        	System.out.println(generateMD5(a));
//        	05f12a4fbcc649374b0c84a41c32bd5d
//        	05f12a4fbcc649374b0c84a41c32bd5d
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}