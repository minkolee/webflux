package cc.conyli.webflux.consumer;

import cc.conyli.webflux.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@Slf4j
public class WebClientConsumer {

    private WebClient webClient = WebClient.create("http://localhost:8888");

    @Test
    public void testConnect() {
        Mono<Employee> employeeMono = webClient.get().uri("/api/employees/{id}", 20)
                .exchange()
                .flatMap(clientResponse -> {
                    if (clientResponse.headers().header("NA").contains(true)) {
                        return Mono.empty();
                    } else {
                        return Mono.just(clientResponse);
                    }
                })
                .flatMap(cr -> cr.bodyToMono(Employee.class));



        log.info(employeeMono.block().toString());

    }

    @Test
    public void testFluxConnect() {
        Flux<Employee> employeeFlux = webClient.get().uri("/api/employees").retrieve().bodyToFlux(Employee.class);


        log.info(employeeFlux.blockFirst().toString());

//        StepVerifier.create(employeeFlux)
//                .expectNextMatches(s -> s.getFirstName().equals("3"))
//                .expectNextMatches(s -> s.getFirstName().equals("2"))
//                .expectNextMatches(s -> s.getFirstName().equals("3"))
//                .expectNextMatches(s -> s.getFirstName().equals("45"))
//                .expectNextMatches(s -> s.getFirstName().equals("5"))
//                .expectNextMatches(s -> s.getFirstName().equals("6"))
//                .expectNextMatches(s -> s.getFirstName().equals("66"))
//                .verifyComplete();
    }

    @Test
    public void postConnect() {
        Employee employee = new Employee("test3", "test3", "test@test3.com");
        Mono<Employee> employeeMono = Mono.just(employee);

        Mono<Employee> employeeMono1 = webClient.post().uri("/api/employees").body(employeeMono, Employee.class).retrieve().bodyToMono(Employee.class);

        System.out.println("*********************************");
        log.info(employeeMono1.block().toString());
        System.out.println("*********************************");

//        StepVerifier.create(employeeMono1).expectNextMatches(s -> s.getFirstName().equals("test")).verifyComplete();
    }

    @Test
    public void deleteConnect() {

        Mono<Employee> employeeMono = webClient.delete().uri("/api/employees/{id}", 12).retrieve().bodyToMono(Employee.class);


        System.out.println("*********************************");
        log.info(employeeMono.block().toString());
        System.out.println("*********************************");

    }
}
