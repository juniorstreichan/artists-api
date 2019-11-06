package dev.juniorstreichan.artists.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=dev,test")
@TestPropertySource("classpath:application-test.properties")
class ApiApplicationTests {

	@Test
	void contextLoads() {
	}

  @Test
  void sum() {
    Assertions.assertThat(1 + 1).isEqualTo(2);
  }

}
