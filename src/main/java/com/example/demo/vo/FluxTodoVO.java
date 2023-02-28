package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FluxTodoVO
{
    private Double key;
    private String todo;
    private Boolean done;
    private String plan_pick;

}
