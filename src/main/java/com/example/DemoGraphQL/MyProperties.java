package com.example.DemoGraphQL;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("myprops")
@Data
public class MyProperties {
    private String version;
    private String artifactId;
    private H2 h2;

    @Data
    public static class H2 {
        private String version;
    }
}
