package dev.juniorstreichan.artists.api;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=dev,test")
@TestPropertySource("classpath:application-test.properties")
class ApiApplicationTests {
}
