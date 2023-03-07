package com.example.minor1.service;

import com.example.minor1.exception.StudentNotFoundException;
import com.example.minor1.model.Student;
import com.example.minor1.request.StudentCreateRequest;

public interface StudentService {
    Student create(StudentCreateRequest studentCreateRequest);
    Student findStudentById(int studentId);
}
