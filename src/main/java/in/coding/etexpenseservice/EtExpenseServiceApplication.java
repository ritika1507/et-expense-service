package in.coding.etexpenseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class EtExpenseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtExpenseServiceApplication.class, args);
	}

}
