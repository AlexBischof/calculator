package rs;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static rs.kryo.KryoMessageBodyProvider.APPLICATION_KRYO;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import calculator.Calculator;
import calculator.CalculatorParams;

@Path("/calculate")
@Produces(TEXT_PLAIN)
public class CalculatorResource {

    private Executor executor;

    public CalculatorResource() {
	this.executor = Executors.newCachedThreadPool();
    }

    @GET
    public void calculate(@QueryParam("a") int a, @QueryParam("b") int b, @Suspended final AsyncResponse asyncResponse) {
	calculate(asyncResponse, new CalculatorParams(a, b));
    }

    private void calculate(final AsyncResponse asyncResponse, CalculatorParams params) {
	executor.execute(() -> {
	    int addResult = new Calculator().add(params);
	    asyncResponse.resume(addResult);
	});
    }

    @POST
    @Consumes({ APPLICATION_JSON, APPLICATION_KRYO })
    public void calculate(CalculatorParams params, @Suspended final AsyncResponse asyncResponse) {
	calculate(asyncResponse, params);
    }
}
