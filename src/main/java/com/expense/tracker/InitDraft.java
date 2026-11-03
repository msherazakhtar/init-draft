package com.expense.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InitDraft {

	public static void main(String[] args) {
		SpringApplication.run(InitDraft.class, args);
		System.out.println("\n>>>>>>Init Draft Is Now Running<<<<<<");
	}

}
