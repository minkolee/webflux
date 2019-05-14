package cc.conyli.webflux.domain;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@Document
public class Course {

    @Id
    private String id;

    @NonNull
    @Size(min=5,message = "At least 5 characters long")
    private String courseName;

    private Date createAt = new Date();

    private List<Student> students;

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
