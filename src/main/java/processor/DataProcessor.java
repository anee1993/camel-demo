package processor;

import com.google.gson.Gson;
import model.Book;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DataProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        var gson = new Gson();
        String exchangeBody = gson.toJson(exchange.getIn().getBody());
        var book = new Gson().fromJson(exchangeBody, Book.class);
        exchange.getIn().setBody(book.toString());
    }
}
