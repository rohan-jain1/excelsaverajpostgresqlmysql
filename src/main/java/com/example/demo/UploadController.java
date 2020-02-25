package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@Autowired
	StudentRepository stuRep;
	
	@Autowired
	FileService fileSer;
	
	
	@ResponseBody
	@PostMapping("/fileupload")
	public String fileUpload(@RequestParam(name="file") MultipartFile file) {
		String infoString = fileSer.saveFile(file);
		if(infoString.isEmpty()) {
			return "dataEnteredSuccessfully";
		}
		return infoString;
	}
	
	
	@GetMapping("/fileform")
	public String fileForm() {
		return  "uploadfile";
	}
	
	
	@ResponseBody
	@RequestMapping("/hello2") 
	public String hello2() {
		Student s = new Student();
		s.setName("roha");
		s.setEmail("test@gmai.com");
		s.setEnglishscore(22);
		s.setMathscore(34);
		stuRep.save(s);
//		
//		
//		Student s2 = new Student();
//		s2.setName("roha");
//		s2.setEnglishscore(100);
//		s2.setMathscore(100);
		
		List<Student> stuList= new ArrayList<>();
		stuList.add(s);
		stuList.add(s);
		
		
		stuRep.saveAll(stuList);
		return "hello2";
	}
	
	@ResponseBody
	@RequestMapping("/deleteall")
	public void deletedata() {
		stuRep.deleteAll();
	}
	
	@ResponseBody
	@RequestMapping("/getdata")
	public Iterable<Student> getdata() {
		return stuRep.findAll();
	}
}
