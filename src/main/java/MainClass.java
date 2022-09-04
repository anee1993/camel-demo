import listener.CDCListener;
import org.apache.camel.CamelContext;
import org.apache.camel.main.Main;
import org.apache.camel.main.MainListener;
import org.apache.camel.main.MainListenerSupport;
import route.DirectToSQLRoute;
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
        String sqlInsertEndpoint = "sql:Select * from books where migrated = false?datasource=#datasource"; //&repeatCount=1

        String directEndpoint = "direct:dummy";
        String sqlUpdateEndpoint = "sql:update books set migrated = true where bookid = :#id";
        String logEndpoint = "log:books.log?level=INFO";

        main.addMainListener(new CDCListener() {
            @Override
            public void configure(CamelContext context) {
                try {
                    context.addRoutes(new SQLToConsoleRoute(sqlInsertEndpoint, directEndpoint, logEndpoint));
                    context.addRoutes(new DirectToSQLRoute(directEndpoint, sqlUpdateEndpoint, logEndpoint));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        main.run();
    }
}