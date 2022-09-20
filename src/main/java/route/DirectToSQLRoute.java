package route;

import org.apache.camel.builder.RouteBuilder;
import processor.DataProcessor;

public class DirectToSQLRoute extends RouteBuilder {

    private final String directEndpoint;
    private final String sqlEndpoint;

    private final String logEndpoint;

    public DirectToSQLRoute(String directRoute, String sqlEndpoint, String logEndpoint) {
        this.directEndpoint = directRoute;
        this.sqlEndpoint = sqlEndpoint;
        this.logEndpoint = logEndpoint;
    }

    @Override
    public void configure() {
        from(directEndpoint)
                .process(new DataProcessor())
                .to(logEndpoint)
                .to(sqlEndpoint)
                .end();

    }
}
