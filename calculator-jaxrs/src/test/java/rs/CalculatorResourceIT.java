package rs;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.assertEquals;
import static rs.kryo.KryoMessageBodyProvider.APPLICATION_KRYO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;

import rs.kryo.KryoMessageBodyProvider;
import calculator.CalculatorParams;

public class CalculatorResourceIT {

    String uri = "http://localhost:8080/calculator/calculate";

    @Test
    public void get() {
	Client client = ClientBuilder.newClient();
	Response response = client.target(uri).queryParam("a", 1).queryParam("b", 2).request().get();
	assertEquals(200, response.getStatus());
    }

    @Test
    public void postJson() {
	CalculatorParams calculatorParams = new CalculatorParams(1, 2);
	Client client = ClientBuilder.newClient();
	Response response = client.target(uri).request().post(entity(calculatorParams, APPLICATION_JSON));
	assertEquals(200, response.getStatus());
    }

    @Test
    public void postKyro() {
	CalculatorParams calculatorParams = new CalculatorParams(1, 2);
	Client client = ClientBuilder.newClient();
	Response response = client.target(uri).register(KryoMessageBodyProvider.class).request().post(entity(calculatorParams, APPLICATION_KRYO));
	assertEquals(200, response.getStatus());
    }
}
