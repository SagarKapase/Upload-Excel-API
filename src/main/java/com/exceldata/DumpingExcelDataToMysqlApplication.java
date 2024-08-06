package com.exceldata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DumpingExcelDataToMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DumpingExcelDataToMysqlApplication.class, args);
	}

}
