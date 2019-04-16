package cc.conyli.webflux.repository;

import cc.conyli.webflux.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
