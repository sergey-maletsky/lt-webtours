package com.maletsky.pochta.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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

    public static String getCsrfToken(final String body) {
        return body.substring(body.lastIndexOf("csrfToken\":\"") + 12, body.indexOf("csrfToken\"") + 76);
    }
}
