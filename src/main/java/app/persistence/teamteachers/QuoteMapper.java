package app.persistence.teamteachers;

import app.entities.teamteachers.Quote;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QuoteMapper {


    public static Quote getPhilosophicalAnswer(String input, ConnectionPool connectionPool) {
        // hardcoded demo data. Should hit the database for real data
        return new Quote("Kierkegaard", "Af alle latterlige ting forekommer det mig at være det allerlatterligste at have travlt", "kierkegaard.png");
    }
}