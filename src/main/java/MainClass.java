import org.apache.camel.main.Main;
import route.DirectToSQLRoute;
import route.SQLToDirectRoute;
import route.SQLToSQLRoute;
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
        //RepeatCount when set to 1 makes this endpoint run only once. We can remove this attribute to make the endpoint a periodic poller
        String sqlGetEndpoint = "sql:Select * from books where migrated = false?datasource=#datasource"; //&repeatCount=1
        String sqlUpdateEndpoint = "sql:update books set migrated = true where bookid = :#id";

        String logEndpoint = "log:books.log?level=INFO";

        main.configure().addRoutesBuilder(new SQLToSQLRoute(sqlGetEndpoint, sqlUpdateEndpoint, logEndpoint));

//        String directEndpoint = "direct:internal.dummy";
//        main.configure().addRoutesBuilder(new SQLToDirectRoute(sqlGetEndpoint, directEndpoint, logEndpoint));
//        main.configure().addRoutesBuilder(new DirectToSQLRoute(directEndpoint, sqlUpdateEndpoint, logEndpoint));

        main.run();

    }
}