package cc.conyli.webflux.controller;

import cc.conyli.webflux.domain.Course;
import cc.conyli.webflux.domain.Student;
import cc.conyli.webflux.repository.CourseRepo;
import cc.conyli.webflux.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

@RestController
@RequestMapping(path = "/flux", produces = "application/json")
public class FluxController {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    private final String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final Random rand = new Random();

    @Autowired
    public FluxController(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }


    @GetMapping("/students")
    public Flux<Student> getStudents() {
        return studentRepo.findAll();
    }


    @GetMapping("/students/{id}")
    public Mono<Student> getSingleStudent(@PathVariable("id") String id) {
        System.out.println("-------------------------------"+ id +"-----------------------------");
        return studentRepo.findById(id);
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

    @GetMapping("/course/write")
    public Mono<Course> writeRandomCourse() {
        int courseId = rand.nextInt(5);
        Mono<Student> studentMono = getSingleStudent("5cb67f4ff8a1fa80f0ec18f3");

        Student student = new Student();
        student.setFirstName(genRandomString());
        student.setLastName(genRandomString());
        student.setCourseId(courseId);

        Student student1 = new Student();
        student1.setFirstName(genRandomString());
        student1.setLastName(genRandomString());
        student1.setCourseId(courseId);

        Course course = new Course();
        course.setCourseName(genRandomString());
        course.addStudent(student);
        course.addStudent(student1);

        return courseRepo.insert(course);
    }

    @GetMapping("/course")
    public Flux<Course> getCourses() {
        return courseRepo.findAll();
    }

    @GetMapping("/change")
    public Flux<Student> changeAllStudentName() {
        Flux<Student> studentFlux = getStudents()
                .map(student -> {
                    Student newS = new Student();
                    newS.setId(student.getId());
                    newS.setFirstName(genRandomString());
                    newS.setLastName(student.getLastName());
                    newS.setCourseId(student.getCourseId());
                    return newS;
                }).subscribeOn(Schedulers.parallel());

        return studentFlux;

    }

    @GetMapping("/change/{id}")
    public Mono<Student> changeSingleName(@PathVariable("id") String id) {

        Mono<Student> studentMono = getSingleStudent(id);

        Mono<Student> newMono = studentMono.map(student -> {
            Student newS = new Student();
            newS.setId(student.getId());
            newS.setFirstName(genRandomString());
            newS.setLastName(student.getLastName());
            newS.setCourseId(student.getCourseId());
            return newS;
        });


        return newMono;
    }
}
