package data;

import model.Quote;
import java.util.ArrayList;
import java.util.List;

public class QuoteData {
    static List<Quote> quotes = new ArrayList<Quote>();
    static {
        quotes.add(createQuote(1, "Never Trust Anything on the Internet.",  "Abraham Lincoln"));
        quotes.add(createQuote(2, "Be yourself; everyone else is already taken.",  "Oscar Wilde"));
        quotes.add(createQuote(3, "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.",  "Albert Einstein"));
        quotes.add(createQuote(4, "If you tell the truth, you don't have to remember anything.",  "Mark Twain"));
    }

    private static Quote createQuote(int id, String quoteText, String author) {
        Quote quote = new Quote();
        quote.setId(id);
        quote.setText(quoteText);
        quote.setAuthor(author);
        return quote;
    }

    public static Quote getQuoteById(Long quoteId) {
        for (Quote quote: quotes ){
            if(quote.getId() == quoteId){
                return quote;
            }
        }
        return null;
    }

    public static void addQuote(Quote quote) {
        quotes.add(quote);
    }

    public static boolean deleteQuote(Long quoteId) {
        if (getQuoteById(quoteId) != null){
            if (quotes.remove(getQuoteById(quoteId))){
                return true;
            }
        }
        return false;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

}
