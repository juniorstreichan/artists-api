package dev.juniorstreichan.artists.api.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public abstract class RepositoryTest {
}
