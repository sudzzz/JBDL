package com.example.minor1.service.Impl;

import com.example.minor1.model.MyUser;
import com.example.minor1.model.Student;
import com.example.minor1.repository.StudentRepository;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.request.UserCreateRequest;
import com.example.minor1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    MyUserDetailsServiceImpl myUserDetailsService;

    @Override
    public Student create(StudentCreateRequest studentCreateRequest) {
        UserCreateRequest userCreateRequest = studentCreateRequest.toStudentUser();
        MyUser myUser = myUserDetailsService.createUser(userCreateRequest);
        Student student = studentCreateRequest.toStudent();
        student.setMyUser(myUser);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

}
