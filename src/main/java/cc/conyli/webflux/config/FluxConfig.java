package cc.conyli.webflux.config;

import cc.conyli.webflux.domain.Student;
import cc.conyli.webflux.repository.StudentRepo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

@Configuration
public class FluxConfig {

    @Autowired
    private StudentRepo studentRepo;


    @Bean
    public RouterFunction<?> helloRouterFuntion() {
        return route(GET("/hello"), request -> ok().body(just("Hello World"), String.class))
                .andRoute(GET("/bye"), serverRequest -> ok().body(just("goodbye"), String.class));
    }

//    @Bean
//    public RouterFunction<?> findALL() {
//        return route(GET("/students"), request -> ok().body(Mono.just(studentRepo.findById(1), St   .class));
//    }

//    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
//        return ServerResponse.ok().body(just("Hello world"), String.class);
//    }

}
