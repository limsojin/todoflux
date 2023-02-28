package com.example.demo.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.entity.FluxTodo;
import com.example.demo.repository.FluxTodoRepository;
import com.example.demo.vo.FluxTodoVO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class FluxTodoHandler
{
    @NonNull
    private final FluxTodoRepository fluxTodoRepository;

    /**
     * <pre>
     * contents
     * 모두 조회
     * </pre>
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getTodoList ( ServerRequest request )
    {
        final Flux<FluxTodo> todo = this.fluxTodoRepository.findAll ();
        final Flux<FluxTodoVO> todoVo = todo.map ( o -> {
            return new FluxTodoVO ( o.getKey (), o.getTodo (), o.getDone (), o.getPlan_pick () );
        } );
        return ServerResponse.ok ().contentType ( MediaType.APPLICATION_JSON ).body ( todoVo, FluxTodoVO.class );
    }

    /**
     * <pre>
     * contents
     * 아이디로 조회
     * </pre>
     *
     * @param request
     * @param key
     * @return
     */
    public Mono<ServerResponse> getTodoListById ( ServerRequest request )
    {
        final String key = request.pathVariable ( "key" );
        final double d = Double.parseDouble ( key );
        final Mono<ServerResponse> notFound = ServerResponse.notFound ().build ();
        final Mono<FluxTodo> todoId = this.fluxTodoRepository.findById ( d );
        final Mono<FluxTodoVO> todoIdVo = Mono.from ( todoId.map ( o -> {
            return new FluxTodoVO ( o.getKey (), o.getTodo (), o.getDone (), o.getPlan_pick () );
        } ) );
        return ServerResponse.ok ().contentType ( MediaType.APPLICATION_JSON ).body ( todoIdVo, FluxTodoVO.class )
                        .switchIfEmpty ( notFound );
    }

    /**
     * <pre>
     * contents
     * 등록
     * </pre>
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> addTodoList ( ServerRequest request )
    {
        Mono<FluxTodoVO> todoVo = request.bodyToMono ( FluxTodoVO.class );
        final Mono<FluxTodo> todo = todoVo.map ( o -> new FluxTodo ( o.getKey (), o.getTodo (), o.getDone (), o.getPlan_pick () ) )
                        .flatMap ( this.fluxTodoRepository::save );
        todoVo = Mono.from ( todo.map ( o -> {
            return new FluxTodoVO ( o.getKey (), o.getTodo (), o.getDone (), o.getPlan_pick () );
        } ) );

        return ServerResponse.ok ().contentType ( MediaType.APPLICATION_JSON ).body ( todoVo, FluxTodoVO.class )
                        .onErrorResume ( error -> ServerResponse.badRequest ().build () );

    }

    /**
     * <pre>
     * contents
     * 수정
     * </pre>
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> updateTodoList ( ServerRequest request )
    {
        Mono<FluxTodoVO> todoVo = request.bodyToMono ( FluxTodoVO.class );
        final Mono<FluxTodo> todo = todoVo.map ( o -> new FluxTodo ( o.getKey (), o.getTodo (), o.getDone (), o.getPlan_pick () ) )
                        .flatMap ( this.fluxTodoRepository::save );
        todoVo = Mono.from ( todo.map ( o -> {
            return new FluxTodoVO ( o.getKey (), o.getTodo (), o.getDone (), o.getPlan_pick () );
        } ) );

        return ServerResponse.ok ().contentType ( MediaType.APPLICATION_JSON ).body ( todoVo, FluxTodoVO.class )
                        .onErrorResume ( error -> ServerResponse.badRequest ().build () );
    }

    /**
     * <pre>
     * contents
     * 삭제
     * </pre>
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> deleteTodoList ( ServerRequest serverRequest )
    {
        final Mono<FluxTodoVO> todoVo = serverRequest.bodyToMono ( FluxTodoVO.class );
        return todoVo.flatMap ( o -> this.fluxTodoRepository.findById ( o.getKey () ) )
                        .flatMap ( o -> this.fluxTodoRepository.delete ( o ) )
                        .then ( ServerResponse.ok ().build ( Mono.empty () ) );
    }
}
