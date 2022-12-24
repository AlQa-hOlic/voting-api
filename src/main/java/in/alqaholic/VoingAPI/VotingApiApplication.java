package in.alqaholic.VoingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Voting API", version = "1.0", description = "Sample Voting API"))
public class VotingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingApiApplication.class, args);
	}

}

@RestController
class TestController {
	@GetMapping("/api/test")
	public String test() {
		return "hello, world!";
	}
}