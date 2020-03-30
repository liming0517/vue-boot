package com.temp;




import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = {
		"com.temp.department.mapper",
		"com.temp.permission.mapper",
		"com.temp.generator.mapper",
		"com.temp.common.mapper",
		"com.wiserun.develop.*",
		"com.wiserun.devolop.system.dao",
		"com.wiserun.common.dao",
		"app.dao",
		"com.temp.custom.mapper",
		"com.wiserun.RestFul.*"
})
@SpringBootApplication
@ComponentScan(basePackages = {"com.wiserun.*","com.temp.*","app.*"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(com.temp.Application.class, args);
	}
	
	
}