package route.example;

import org.apache.camel.builder.RouteBuilder;

public class DirectToFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:sampleInput")
                .log("Received Message is ${body} and Headers are ${headers}")
                .to("file:sampleOutput?fileName=output.txt");
    }
}
