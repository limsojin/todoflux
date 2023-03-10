package com.example.demo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FluxTodo;

@Repository
public interface FluxTodoRepository extends ReactiveCrudRepository<FluxTodo, Double>
{

}
