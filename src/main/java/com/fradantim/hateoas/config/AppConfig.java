package com.fradantim.hateoas.config;

import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.fradantim.hateoas.persistence.entity.Car;
import com.fradantim.hateoas.persistence.entity.Person;
import com.fradantim.hateoas.persistence.repository.CarRepository;
import com.fradantim.hateoas.persistence.repository.PersonRepository;
import com.github.javafaker.Faker;

@Configuration
public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	@Value("${mock.person.ammount}")
	private Integer mockPersonAmmount;
	
	@Value("${mock.car.from}")
	private Integer mockCarFrom;
	
	@Value("${mock.car.to}")
	private Integer mockCarTo;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private CarRepository carRepository;

	@PostConstruct
	private void postConstruct() {
		for (int i = 0; i < mockPersonAmmount; i++) {
			Person p = new Person(Faker.instance().name().fullName(), Faker.instance().date().birthday(18, 95).toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate());
			int carsAmmount = Faker.instance().random().nextInt(mockCarFrom, mockCarTo);
			logger.info("Persisting... {}/{},  {} cars", i + 1, mockPersonAmmount, carsAmmount);
			p = personRepository.save(p);

			for (int c = 0; c < carsAmmount; c++) {
				carRepository.save(new Car(p, Faker.instance().animal().name()));
			}
		}
	}
}
