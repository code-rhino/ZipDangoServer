package io.zipcoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


@SpringBootApplication
@RestController
public class FandangoServerApplication {

	private static final Logger log = LoggerFactory.getLogger(FandangoServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FandangoServerApplication.class, args);
	}

	@RequestMapping("/")
	public String helloWorld(){
		return "Hello";
	}

	@RequestMapping("/movies")
	public ShowResponse[] movies(){
		ShowResponse [] responses = null;
		try {
			responses = getShowResponse();
		} catch (Exception ex){
			log.info("Oooooo noooooooo");
		}
		return responses;
	}

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	private ShowResponse[] getShowResponse() throws Exception{
		Resource resource = applicationContext.getResource("classpath:demoData.json");
		InputStream is = resource.getInputStream();
		StringBuilder textBuilder = new StringBuilder();
		try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))){
			int c = 0;
			while ((c = reader.read()) != -1){
				textBuilder.append((char) c);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		ShowResponse[] responseBody = mapper.readValue(textBuilder.toString(), ShowResponse[].class);
		return responseBody;
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
		return args->{
			Resource resource = applicationContext.getResource("classpath:demoData.json");
			InputStream is = resource.getInputStream();
			StringBuilder textBuilder = new StringBuilder();
			try (Reader reader = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))){
				int c = 0;
				while ((c = reader.read()) != -1){
					textBuilder.append((char) c);
				}
			}
			ObjectMapper mapper = new ObjectMapper();
			ShowResponse[] responseBody = mapper.readValue(textBuilder.toString(), ShowResponse[].class);
			log.info(responseBody[0].toString());
		};
	}
}
