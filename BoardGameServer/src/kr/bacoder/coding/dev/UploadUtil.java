package kr.bacoder.coding.dev;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;

import kr.bacoder.coding.bean.Person;
import kr.bacoder.coding.bean.Photo;

public class UploadUtil {
	Logger logger = Logger.getLogger(UploadUtil.class.getSimpleName());
	
	public String setPhoto(String path, String imgEncodedStr, String fileName, int patientId) {
		logger.info(path);
		
		StringBuilder photoUrl = new StringBuilder();
		//photoUrl.append("http://www.bacoder.kr/upload/").append("/");
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(new Date());
		
		File parentPath = new File(path);
		if(!parentPath.exists()) {
			parentPath.mkdirs();
		}
		// Write Image into File system - Make sure you update the path
		File file = new File(path + File.separator + timeStamp + ext);
		photoUrl.append(file.getName());
		
		try(FileOutputStream imageOutFile = new FileOutputStream(file)){
			byte[] imageByteArray = Base64.decodeBase64(imgEncodedStr); 
			imageOutFile.write(imageByteArray);			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return photoUrl.toString();
	}
	public Person setFile(String path, String imgEncodedStr, String fileName, Person person){
		
		try {
			// Decode String using Base64 Class
			byte[] imageByteArray = Base64.decodeBase64(imgEncodedStr); 
			
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(new Date());
			
			// Write Image into File system - Make sure you update the path
			File file = new File(path + File.separator + timeStamp + ext);
			FileOutputStream imageOutFile = new FileOutputStream(file);
			imageOutFile.write(imageByteArray);
			person.setPhoto("http://www.bacoder.kr/upload/" + file.getName());
			imageOutFile.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Image Path not found" + fnfe);
		} catch (IOException ioe) {
			System.out.println("Exception while converting the Image " + ioe);
		}
		return person;
	}
}
