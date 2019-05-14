package cc.conyli.webflux.controller;

import cc.conyli.webflux.domain.Student;
import cc.conyli.webflux.repository.StudentRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@RestController
@RequestMapping(path = "/flux", produces = "application/json")
public class FluxController {

    private StudentRepo studentRepo;

    private final String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final Random rand = new Random();

    @Autowired
    public FluxController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @GetMapping("/students")
    public Flux<Student> getStudents() {
        return studentRepo.findAll();
    }


    @GetMapping("/students/{id}")
    public Mono<Student> getSingleStudent(@PathVariable("id") String id) {
        return studentRepo.findById(new ObjectId(id));
    }

    @GetMapping("/student/write")
    public Mono<Student> writeRandomStudent() {
        int courseId = rand.nextInt(5);
        Student student = new Student();
        student.setFirstName(genRandomString());
        student.setLastName(genRandomString());
        student.setCourseId(courseId);
        return studentRepo.save(student);
    }

    private String genRandomString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(base.charAt(rand.nextInt(62)));
        }

        return stringBuilder.toString();
    }


}
