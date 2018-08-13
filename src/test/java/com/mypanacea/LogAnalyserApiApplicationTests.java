package com.mypanacea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@SpringBootTest
public class LogAnalyserApiApplicationTests {

	@Test
	public void contextLoads() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss,SSS");
//2015-10-26T16:10:08,468
		System.out.println(LocalDateTime.now().format(formatter));
	}

}
