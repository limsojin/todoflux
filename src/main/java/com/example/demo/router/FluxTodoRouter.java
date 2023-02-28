package com.example.demo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.handler.FluxTodoHandler;

@Configuration
public class FluxTodoRouter
{
    @Bean
    public RouterFunction<ServerResponse> route ( FluxTodoHandler handler )
    {
        return RouterFunctions
                        .route ( RequestPredicates.GET ( "/todo" ).and ( RequestPredicates.accept ( MediaType.APPLICATION_JSON ) ),
                                        handler::getTodoList )
                        .andRoute (
                                        RequestPredicates.GET ( "/todo/{key}" )
                                                        .and ( RequestPredicates.accept ( MediaType.APPLICATION_JSON ) ),
                                        handler::getTodoListById )
                        .andRoute ( RequestPredicates.POST ( "/todo/save" ).and ( RequestPredicates.accept ( MediaType.APPLICATION_JSON ) )
                                        .and ( RequestPredicates.contentType ( MediaType.APPLICATION_JSON ) ), handler::addTodoList )
                        .andRoute ( RequestPredicates.PUT ( "/todo/{key}" ).and ( RequestPredicates.accept ( MediaType.APPLICATION_JSON ) )
                                        .and ( RequestPredicates.contentType ( MediaType.APPLICATION_JSON ) ), handler::updateTodoList )
                        .andRoute ( RequestPredicates.DELETE ( "/todo/{key}" )
                                        .and ( RequestPredicates.accept ( MediaType.APPLICATION_JSON ) )
                                        .and ( RequestPredicates.contentType ( MediaType.APPLICATION_JSON ) ), handler::deleteTodoList );
    }
}
