package test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import regist.RegistDao;

@Service
public class ReportService {
	
	
	public void upload(MultipartFile report) {
		String name=report.getOriginalFilename();
		int index=name.lastIndexOf(".");
		String ext=name.substring(index);
		String n_name=System.currentTimeMillis()+"_"+new Random().nextInt(50)+"."+ext;
		File n_file=new File("d://syshinsmile//back-end//testImage//"+n_name);
		try { report.transferTo(n_file);}
		catch (IllegalStateException e) {e.printStackTrace();}
		catch(IOException e) {e.printStackTrace();}
		System.out.println(n_file.getPath());
	}
	

}
