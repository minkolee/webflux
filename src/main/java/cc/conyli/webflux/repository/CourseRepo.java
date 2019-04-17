package cc.conyli.webflux.repository;

import cc.conyli.webflux.domain.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
public interface CourseRepo extends ReactiveMongoRepository<Course, Integer> {
    Mono<Course> findById(String id);
}
