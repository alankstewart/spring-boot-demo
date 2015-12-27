package alankstewart.store;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"alankstewart.store.core", "alankstewart.store.order"})
@Import(InfrastructureConfig.class)
public class ApplicationConfig {
}
