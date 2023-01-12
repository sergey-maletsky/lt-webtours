package com.maletsky.webtours.util;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public final class ContentParser {

    private Document document;

    private ContentParser() {
    }

    private ContentParser(final Document document) {
        this.document = document;
    }

    public static ContentParser parse(final String body) {
         return new ContentParser(Jsoup.parse(body));
    }

    public String getOutboundFlight() {
        final Elements options = document.getElementsByAttributeValue("name", "outboundFlight");
        return getRandomElement(options, "");
    }

    public String getDepartureCity() {
        final Elements options = document.getElementsByAttributeValue("name", "depart").get(0).children();
        return getRandomElement(options, "");
    }

    public String getArrivalCity(final String departureCity) {
        final Elements options = document.getElementsByAttributeValue("name", "arrive").get(0).children();
        return getRandomElement(options, departureCity);
    }

    public String getDepartureDate() {
        return document.getElementsByAttributeValue("name", "departDate").get(0).val();
    }

    public String getReturnDate() {
        return document.getElementsByAttributeValue("name", "returnDate").get(0).val();
    }

    private static String getRandomElement(final Elements elements, final String exclusionItem) {
        final List<String> optionValues = elements.stream()
                .map(el -> el.attr("value"))
                .filter(el -> !el.equals(exclusionItem))
                .collect(Collectors.toList());
        return getRandomElement(optionValues);
    }

    private static String getRandomElement(final List<String> list) {
        final Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
