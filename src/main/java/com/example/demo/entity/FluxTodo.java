package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table ( "newtodo" )
public class FluxTodo
{
    @Id
    private Double key;
    private String todo;
    private Boolean done;
    private String plan_pick;
}
