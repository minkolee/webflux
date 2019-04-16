package cc.conyli.webflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(force = true)
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String courseName;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courseId")
    private final List<Student> students = new ArrayList<>();

    void add(Student student) {
        this.students.add(student);
    }

}
