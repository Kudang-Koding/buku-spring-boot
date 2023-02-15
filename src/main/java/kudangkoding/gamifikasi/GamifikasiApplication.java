package kudangkoding.gamifikasi;

import kudangkoding.gamifikasi.configs.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableScheduling
public class GamifikasiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamifikasiApplication.class, args);
	}

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

}
