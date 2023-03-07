package com.example.minor1.controller;

import com.example.minor1.exception.StudentNotFoundException;
import com.example.minor1.model.MyUser;
import com.example.minor1.model.Student;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        return new ResponseEntity<>(studentService.create(studentCreateRequest), HttpStatus.CREATED);
    }

    //Only for Student
    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() throws StudentNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(Objects.isNull(myUser.getStudent())){
            throw new StudentNotFoundException("User requesting the details is not a student!");
        }else {
            int studentId = myUser.getStudent().getId();
            return new ResponseEntity<>(studentService.findStudentById(studentId),HttpStatus.ACCEPTED);
        }

    }

    //Only for Admins
    @GetMapping("/student_for_admin")
    public ResponseEntity<Student> getStudentForAdmin(@RequestParam("studentId") int studentId) throws StudentNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(Objects.isNull(myUser.getAdmin())){
            throw new StudentNotFoundException("User requesting the details is not an Admin!");
        }else{
            return new ResponseEntity<>(studentService.findStudentById(studentId),HttpStatus.ACCEPTED);
        }
    }
}
