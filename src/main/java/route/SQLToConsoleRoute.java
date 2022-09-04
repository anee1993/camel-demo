package route;

import org.apache.camel.builder.RouteBuilder;

public class SQLToConsoleRoute extends RouteBuilder {

    private final String sqlEndpoint;

    private final String directEndpoint;

    private final String logEndpoint;


    public SQLToConsoleRoute(String sqlEndpoint, String directEndpoint, String logEndpoint) {
        this.sqlEndpoint = sqlEndpoint;
        this.directEndpoint = directEndpoint;
        this.logEndpoint = logEndpoint;
    }

    @Override
    public void configure() {
        from(sqlEndpoint)
                .setHeader("id", simple("${body[bookid]}"))
                .to(logEndpoint)
                .to(directEndpoint)
                .end();
    }
}
