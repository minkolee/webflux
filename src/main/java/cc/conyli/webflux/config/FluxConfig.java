package cc.conyli.webflux.config;

import cc.conyli.webflux.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

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

    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("/time"), req -> timeHandler.getTime(req))
                .andRoute(GET("/date"), timeHandler::getDate)
                .andRoute(GET("/times"),timeHandler::sendTimePerSec);  // 这种方式相对于上一行更加简洁
    }

}
