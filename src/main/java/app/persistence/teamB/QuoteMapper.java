package app.persistence.teamB;

import app.entities.teamB.Quotes;
import app.persistence.ConnectionPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteMapper {

    public static Quotes getRandomQuote(ConnectionPool connectionPool) {

        List<Quotes> quotes = new ArrayList<>();

        quotes.add(new Quotes("You are not behind. You are becoming."));
        quotes.add(new Quotes("Small steps still move you forward."));
        quotes.add(new Quotes("The version of you that you dream about is already forming."));
        quotes.add(new Quotes("Keep going. One day it will all make sense."));
        quotes.add(new Quotes("You survived every hard day before this one too."));
        quotes.add(new Quotes("You do not need permission to grow."));
        quotes.add(new Quotes("Progress is still progress, no matter how slow."));
        quotes.add(new Quotes("Your future needs the effort you make today."));
        quotes.add(new Quotes("Be proud of how far you have come."));
        quotes.add(new Quotes("The world is better with you in it."));
        quotes.add(new Quotes("Even now, your story is still unfolding beautifully."));
        quotes.add(new Quotes("You are allowed to start again, stronger and wiser."));
        quotes.add(new Quotes("Hard seasons do not last forever."));
        quotes.add(new Quotes("Your light does not disappear just because the day is cloudy."));
        quotes.add(new Quotes("Believe in the person you are becoming."));

        Random random = new Random();
        int randomIndex = random.nextInt(quotes.size());

        return quotes.get(randomIndex);
    }
}