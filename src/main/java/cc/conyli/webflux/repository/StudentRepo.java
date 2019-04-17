package cc.conyli.webflux.repository;

import cc.conyli.webflux.domain.Student;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
public interface StudentRepo extends ReactiveCrudRepository<Student, Integer> {
    Mono<Student> findById(String id);

    Mono<Student> save(Mono<Student> studentMono);
}
