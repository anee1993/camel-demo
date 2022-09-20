package route;

import org.apache.camel.builder.RouteBuilder;
import processor.DataProcessor;

public class SQLToSQLRoute extends RouteBuilder {

    private final String sqlFromEndpoint;

    private final String sqlToEndpoint;

    private final String logEndpoint;


    public SQLToSQLRoute(String sqlFromEndpoint, String sqlToEndpoint, String logEndpoint) {
        this.sqlFromEndpoint = sqlFromEndpoint;
        this.sqlToEndpoint = sqlToEndpoint;
        this.logEndpoint = logEndpoint;
    }

    @Override
    public void configure() {
        from(sqlFromEndpoint)
                .setHeader("id", simple("${body[bookid]}"))
                .process(new DataProcessor())
                .to(logEndpoint)
                .to(sqlToEndpoint)
                .end();
    }
}
