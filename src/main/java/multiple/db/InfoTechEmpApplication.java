package multiple.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "multiple.db")
public class InfoTechEmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoTechEmpApplication.class, args);
	}

}
