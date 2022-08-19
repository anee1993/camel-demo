package route;

import org.apache.camel.builder.RouteBuilder;
import processor.DataProcessor;

public class SQLToConsoleRoute extends RouteBuilder {

    private final String sqlEndpoint;
    private final String logConsoleEndpoint;


    public SQLToConsoleRoute(String sqlEndpoint, String logConsoleEndpoint) {
        this.sqlEndpoint = sqlEndpoint;
        this.logConsoleEndpoint = logConsoleEndpoint;
    }

    @Override
    public void configure() throws Exception {
        from(sqlEndpoint)
                .process(new DataProcessor())
                .to(logConsoleEndpoint)
        .end();
    }
}
