package com.s4.students.core.services;

import com.s4.students.StudentSolution;
import com.s4.students.core.entities.Student;
import com.s4.students.core.utils.SingleInstanceStudent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StudentSolution.class)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void createTest() {
        Student student = buildStudent();
        int size = studentService.getAll().size();
        studentService.save(student);

        Assert.assertEquals(size + 1, studentService.getAll().size());
    }

    @Test
    public void getAllStudentsTest() {
        Student student1 = buildStudent();
        Student student2 = SingleInstanceStudent.getInstance().getInstanceStudent("Raj", "Kumar");
        int size = studentService.getAll().size();

        studentService.save(student1);
        studentService.save(student2);
        Assert.assertEquals(size + 2, studentService.getAll().size());
    }

    @Test
    public void removeTest() {
        Student student = studentService.save(buildStudent());
        int size = studentService.getAll().size();
        studentService.delete(student.getId());

        Assert.assertEquals(size - 1, studentService.getAll().size());
    }

    private Student buildStudent() {
        return SingleInstanceStudent.getInstance().getInstanceStudent("John", "Doe");
    }
}
