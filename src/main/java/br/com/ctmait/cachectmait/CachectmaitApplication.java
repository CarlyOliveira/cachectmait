package br.com.ctmait.cachectmait;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CachectmaitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachectmaitApplication.class, args);
	}

}
