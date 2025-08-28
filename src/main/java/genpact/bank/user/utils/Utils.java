package genpact.bank.user.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.web.multipart.MultipartFile;

public class Utils {

	public static String generateSalt() {
		 
		final int SALT_LENGTH = 10;
 
		String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
 
		StringBuilder strSalt = new StringBuilder(SALT_LENGTH);
 
		SecureRandom random = new SecureRandom();
 
		for (int i = 0; i < SALT_LENGTH; i++) {
 
			int randomIndex = random.nextInt(saltChars.length());
 
			strSalt.append(saltChars.charAt(randomIndex));
 
		}
 
		return strSalt.toString();
	}
 
	public static String generateHash(String inputString) {
		String strHash = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = messageDigest.digest(inputString.getBytes());
			strHash = bytesToHex(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return strHash;
	}
 
	private static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append("0");
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	
	public static Blob convertToBlob(MultipartFile multipartFile) {
		Blob blobFile = null;
		try {
			byte [] fileBytes = multipartFile.getBytes();
			blobFile = new SerialBlob(fileBytes);
		} catch(IOException | SQLException e){
			e.printStackTrace();
		}
		return blobFile;
	}
	
	public static MultipartFile converToMultipart(Blob image) {
		 
		byte[] imageBytes = null;
	 
		try {
			imageBytes = image.getBytes(1, (int) image.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	 
		MultipartFile multipartImage = new MultipartFileImpl(imageBytes, "image.png", "image/png");
	 
		return multipartImage;
	 
	}
	
	public static String blobToBase64(Blob blob) {
	    try {
	        if (blob != null) {
	            byte[] bytes = blob.getBytes(1, (int) blob.length());
	            return Base64.getEncoder().encodeToString(bytes);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static String getBase64Image(byte[] imageBytes) {
		return Base64.getEncoder().encodeToString(imageBytes);
	}
}
