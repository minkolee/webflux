package cc.conyli.webflux.controller;

import cc.conyli.webflux.domain.Student;
import cc.conyli.webflux.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/flux", produces = "application/json")
public class FluxController {

    private StudentRepo studentRepo;

    @Autowired
    public FluxController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    @GetMapping("/students")
    public Flux<Student> getStudents() {
        return Flux.fromIterable(studentRepo.findAll());
    }


    @GetMapping("/students/{id}")
    public Mono<Student> getSingleStudent(@PathVariable("id") int id) {
        return Mono.just(studentRepo.findById(id).orElse(null));
    }

}
