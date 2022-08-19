import org.apache.camel.CamelContext;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainListenerSupport;
import route.SQLToConsoleRoute;
import util.DataSourceHandler;
import util.PropertiesFileParser;

import javax.sql.DataSource;
import java.util.Properties;

public class MainClass {

    private static final Properties props = new PropertiesFileParser().getProperties();

    private static final DataSourceHandler dataSourceHandler = new DataSourceHandler(
            props.getProperty("url"),
            props.getProperty("username"),
            props.getProperty("password"),
            props.getProperty("driver")
    );

    private static final DataSource dataSource = dataSourceHandler.getDataSource();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.bind("datasource", dataSource);
        String fromEndpoint = "sql:Select * from temp?datasource=#datasource&repeatCount=1";
        String toEndpoint = "log:books.log?level=INFO";

        main.addMainListener(new MainListenerSupport() {
            @Override
            public void configure(CamelContext context) {
                try {
                    context.addRoutes(new SQLToConsoleRoute(fromEndpoint, toEndpoint));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        main.run();
    }
}