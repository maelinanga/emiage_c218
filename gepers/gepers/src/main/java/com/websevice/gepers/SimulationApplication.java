package com.websevice.gepers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.websevice.gepers.CustomProperties;

import lombok.Data;


@Data
@SpringBootApplication
public class SimulationApplication{
	
	
	public static void main(String[] args) {
		SpringApplication.run(SimulationApplication.class, args);
	}

	

}
