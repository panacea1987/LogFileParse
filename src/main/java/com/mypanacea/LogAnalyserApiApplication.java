package com.mypanacea;

import com.mypanacea.service.Engine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LogAnalyserApiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LogAnalyserApiApplication.class, args);
		System.out.println("===========================================================================================");
		System.out.println("|                            Welcome to log-analyser API                                  |");
		System.out.println("===========================================================================================");
		ctx.getBean(Engine.class).getStatisticFromLogFile();
	}


}
