package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories ( "com.example.demo.repository" )
@EntityScan ( "com.example.demo.entity" )
@SpringBootApplication
public class Todoflux2Application
{

    public static void main ( String [] args )
    {
        SpringApplication.run ( Todoflux2Application.class, args );
    }

}
