package com.example.DemoGraphQL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = DemoGraphQlApplicationTests.Config.class)
@EnableAutoConfiguration
@EnableConfigurationProperties(MyProperties.class)
public class DemoGraphQlApplicationTests {
    @Autowired
    MyProperties properties;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    static class Config {}

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertNotNull(restTemplate);
        assertNotNull(jdbcTemplate);
        assertNotNull(dataSource);
    }

    @Test
    public void h2Connection() throws SQLException {
        try (Connection conn = dataSource.getConnection()) { //DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
            assertNotNull(conn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT 1");
            assertThat(rs).isNotNull();
        }
    }

    @Test
    public void h2Version() {
        String actualVersion = jdbcTemplate.queryForObject("SELECT `VALUE` FROM INFORMATION_SCHEMA.SETTINGS WHERE NAME = 'CREATE_BUILD'", String.class);
        String expectedVersion = properties.getH2()
                                           .getVersion();
        assertThat(expectedVersion).endsWith("." + actualVersion); // the db must be created with the same h2 version in our build
    }

    @Test
    public void artifactProperty() throws IOException {
        assertThat(properties.getArtifactId()).isEqualTo("DemoGraphQL");
    }
}
