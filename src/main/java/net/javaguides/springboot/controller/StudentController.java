package net.javaguides.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.bean.Student;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@GetMapping("/student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(1,"Ramesh","Fadatare");
		//return new ResponseEntity<Student>(student, HttpStatus.OK)
		return ResponseEntity.ok().header("custom-header", "ramesh").body(student);
	}
	
	@GetMapping()
	public ResponseEntity<List<Student>> getStudents(){
		List<Student> students = new ArrayList<>();
		students.add(new Student(1,"Ramesh","Fadatare"));
		students.add(new Student(2, "Umesh", "Fadatare"));
		students.add(new Student(3, "Ram", "Jhadav"));
		students.add(new Student(4, "Sanjay", "Pawar"));
		return ResponseEntity.ok(students);
	}
	
	@GetMapping("/{id}/{first-name}/{last-name}")
	public ResponseEntity<Student> studentPathVariable(@PathVariable int id, 
			@PathVariable("first-name") String firstName, 
			@PathVariable("last-name") String lastName) {
		Student student = new Student(id, firstName, lastName);
		return ResponseEntity.ok(student);
	}
	
	// http://localhost:8080/students/query?id=6&firstName=Ramesh&lastName=Fadatare
	
	@GetMapping("/query")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
										  @RequestParam String firstName,
										  @RequestParam String lastName) {
		Student student = new Student(id, firstName, lastName);
		return ResponseEntity.ok(student);
	}
	
	@PostMapping("/create")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student student) {
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return ResponseEntity.ok(student);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		System.out.println(id);
		return ResponseEntity.ok("Student deleted successfully");
	}
}
