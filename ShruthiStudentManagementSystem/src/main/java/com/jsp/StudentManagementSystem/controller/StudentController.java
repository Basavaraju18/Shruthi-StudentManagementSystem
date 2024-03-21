
package com.jsp.StudentManagementSystem.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.StudentManagementSystem.dto.MessageData;
import com.jsp.StudentManagementSystem.dto.StudentRequest;
import com.jsp.StudentManagementSystem.dto.StudentResponse;
import com.jsp.StudentManagementSystem.entity.Student;
import com.jsp.StudentManagementSystem.service.StudentService;
import com.jsp.StudentManagementSystem.utility.ResponseStructure;

@RestController
//@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService service;
    
	
	//@RequestMapping(method = RequestMethod.POST, value="save")
	@CrossOrigin
	@PostMapping("/student")
	public ResponseEntity<ResponseStructure<StudentResponse>> saveStudent(@RequestBody  StudentRequest studentReq){
		return service.saveStudent(studentReq);
	}
//	@RequestParam will allow us to pass input through the post man 
//	@PutMapping("/student")
//	public ResponseEntity<Student> update(@RequestBody  Student student,
//			@RequestParam int studentId){
//		return service.updateStudent(student, studentId);
//	}
	
	
	//@PathVariable will allow user to get data through the url
	//helps to solve the confusion when we have same url for same method
//	@PutMapping("/{studentId}")
//	public ResponseEntity<ResponseStructure<Student>> update(@RequestBody Student student,
//			@PathVariable int studentId){
//		return service.updateStudent(student, studentId);
//	}
	
//	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<ResponseStructure<Student>> delete(@PathVariable int studentId){
		return service.deleteStudent(studentId);
	}
	
	@GetMapping("/student/{studentId}")
	public ResponseEntity<ResponseStructure<Student>> findStudentById(@PathVariable int studentId){
		return service.findStudentById(studentId);
	}
	@CrossOrigin
	@GetMapping("/students")
	public ResponseEntity<ResponseStructure<List<Student>>> findAll(){
		return service.findAllStudent();
	}
	
	@GetMapping(params = "studentEmail")
	public ResponseEntity<ResponseStructure<StudentResponse>> findByEmail(@RequestParam String studentEmail){
		return service.findByEmail(studentEmail);
	}
	
	
	@PostMapping("/student/extract")
	public ResponseEntity<String> ExtraxtDataFromExcel(@RequestParam MultipartFile file) throws IOException{
		return service.extractDataFromExcel(file);
	}
	
	@PostMapping("/student/write/cxecl")
	public ResponseEntity<String> writeToExcel(@RequestParam String filePath) throws IOException{
		return service.writeToExcel(filePath);
	}
	@PostMapping("/student/send/mail")
	public ResponseEntity<String> javaSendMail(@RequestBody MessageData message) throws IOException{
	 return service.sendMail(message);
	}
	
	//sending html file as mail
	@PostMapping("/student/sendMimeMail")
	public ResponseEntity<String> javaMimeMail(@RequestBody MessageData messageData) throws IOException, MessagingException{
		 return service.sendMimeMessage(messageData);
		}
		
}

