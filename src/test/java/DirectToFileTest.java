import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import route.example.DirectToFileRoute;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DirectToFileTest extends CamelTestSupport {

    @Override
    public RouteBuilder createRouteBuilder() throws Exception
    {
        return new DirectToFileRoute();
    }

    @Test
    public void test() throws Exception {
        template.sendBody("direct:sampleInput","Hello");
        Thread.sleep(5000);

        File file=new File("sampleOutput");
        assertTrue(file.isDirectory());

        Exchange exchange = consumer.receive("file:sampleOutput");

        System.out.println("Received body is :" + exchange.getIn().getBody());
        System.out.println("File Name is :" + exchange.getIn().getHeader("CamelFileName"));
        assertEquals("output.txt", exchange.getIn().getHeader("CamelFileName"));
    }

}