package alankstewart.store.services.web;

import alankstewart.store.order.OrderWebApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OrderWebApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class WebOrderControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void shouldFindById() throws Exception {
        base = new URL("http://localhost:" + port + "/order/1");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        String expected = "{\"@id\":1,\"id\":1,\"version\":0,\"customer\":{\"@id\":2,\"id\":1,\"version\":0,\"firstname\":\"Alan\",\"lastname\":\"Stewart\",\"emailAddress\":{},\"addresses\":[{\"@id\":3,\"id\":1,\"version\":0,\"street\":\"2 Jubilee Street\",\"suburb\":\"Wahroonga\",\"state\":\"NSW\",\"postcode\":\"2076\"},{\"@id\":4,\"id\":2,\"version\":0,\"street\":\"65 Martin Place\",\"suburb\":\"Sydney\",\"state\":\"NSW\",\"postcode\":\"2000\"}]},\"billingAddress\":4,\"shippingAddress\":4,\"lineItems\":[{\"@id\":5,\"id\":1,\"version\":0,\"product\":{\"@id\":6,\"id\":1,\"version\":0,\"name\":\"iPad\",\"description\":\"Apple tablet device\",\"price\":499.00,\"attributes\":{\"connector\":\"socket\"}},\"price\":499.00,\"amount\":2,\"total\":998.00},{\"@id\":7,\"id\":2,\"version\":0,\"product\":{\"@id\":8,\"id\":2,\"version\":0,\"name\":\"MacBook Pro\",\"description\":\"Apple notebook\",\"price\":1299.00,\"attributes\":{}},\"price\":1299.00,\"amount\":1,\"total\":1299.00}],\"total\":2297.00}";
        assertThat(response.getBody(), equalTo(expected));
    }

    @Test
    public void shouldNotFindOrder() throws Exception {
        base = new URL("http://localhost:" + port + "/order/2");
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }
}
