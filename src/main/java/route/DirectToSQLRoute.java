package route;

import org.apache.camel.builder.RouteBuilder;
import processor.DataProcessor;

public class DirectToSQLRoute extends RouteBuilder {

    private final String directRoute;
    private final String sqlRoute;

    private final String logEndpoint;

    public DirectToSQLRoute(String directRoute, String sqlRoute, String logEndpoint) {
        this.directRoute = directRoute;
        this.sqlRoute = sqlRoute;
        this.logEndpoint = logEndpoint;
    }

    @Override
    public void configure() {
        from(directRoute)
                .process(new DataProcessor())
                .to(logEndpoint)
                .to(sqlRoute)
                .end();

    }
}
