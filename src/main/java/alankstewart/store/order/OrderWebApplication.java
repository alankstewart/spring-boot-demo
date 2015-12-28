package alankstewart.store.order;

import alankstewart.store.services.order.OrderServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * The order web-application. This class has two uses:
 * <ol>
 * <li>Provide configuration and setup for {@link OrderServer} ... or</li>
 * <li>Run as a stand-alone Spring Boot web-application for testing (in which
 * case there is <i>no</i> microservice registration</li>
 * </ol>
 * <p>
 * To execute as a microservice, run {@link OrderServer} instead.
 */
@SpringBootApplication
@EnableTransactionManagement
@EntityScan({"alankstewart.store.core", "alankstewart.store.order"})
@EnableJpaRepositories({"alankstewart.store.core", "alankstewart.store.order"})
@PropertySource("classpath:db-config.properties")
public class OrderWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderWebApplication.class, args);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:testdb/schema.sql")
                .addScript("classpath:testdb/data.sql")
                .build();
    }
}
