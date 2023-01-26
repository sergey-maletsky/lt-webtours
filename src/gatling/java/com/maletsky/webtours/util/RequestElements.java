package com.maletsky.webtours.util;

import java.util.Map;

import static com.maletsky.webtours.util.Constants.baseUrl;

public final class RequestElements {

    // headers
    public static final Map<CharSequence, String> loginHeaders = Map.ofEntries(
            Map.entry("Referer", baseUrl + "/cgi-bin/nav.pl?in=home"),
            Map.entry("Origin", baseUrl),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"));
    public static final Map<CharSequence, String> findFlightHeaders = Map.ofEntries(
            Map.entry("Referer", baseUrl + "/cgi-bin/reservations.pl?page=welcome"),
            Map.entry("Origin", baseUrl),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"));
    public static final Map<CharSequence, String> flightHeaders = Map.ofEntries(
            Map.entry("Referer", baseUrl + "/cgi-bin/reservations.pl"),
            Map.entry("Origin", baseUrl),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"));

    // form parameters
    public static final Map<String, Object> loginParameters = Map.ofEntries(
            Map.entry("userSession", "#{userSession}"),
            Map.entry("username", "#{login}"),
            Map.entry("password", "#{password}"),
            Map.entry("login.x", "58"),
            Map.entry("login.y", "6"),
            Map.entry("JSFormSubmit", "off"));
    public static final Map<String, Object> findFlightParameters = Map.ofEntries(
            Map.entry("advanceDiscount", "0"),
            Map.entry("depart", "#{departureCity}"),
            Map.entry("departDate", "#{departureDate}"),
            Map.entry("arrive", "#{arrivalCity}"),
            Map.entry("returnDate", "#{returnDate}"),
            Map.entry("numPassengers", "1"),
            Map.entry("seatPref", "None"),
            Map.entry("seatType", "Coach"),
            Map.entry("findFlights.x", "57"),
            Map.entry("findFlights.y", "3"),
            Map.entry(".cgifields", "roundtrip"));
    public static final Map<String, Object> flightParameters = Map.ofEntries(
            Map.entry("outboundFlight", "#{outboundFlight}"),
            Map.entry("numPassengers", "1"),
            Map.entry("advanceDiscount", "0"),
            Map.entry("seatType", "Coach"),
            Map.entry("seatPref", "None"),
            Map.entry("findFlights.x", "37"),
            Map.entry("findFlights.y", "9"));
    public static final Map<String, Object> paymentFlightParameters = Map.ofEntries(
            Map.entry("firstName", "Sergio"),
            Map.entry("lastName", "Maletti"),
            Map.entry("address1", "Via S. Mirocle, 7"),
            Map.entry("address2", "Milan"),
            Map.entry("pass1", "Sergio Maletti"),
            Map.entry("creditCard", ""),
            Map.entry("expDate", ""),
            Map.entry("oldCCOption", ""),
            Map.entry("numPassengers", "1"),
            Map.entry("seatType", "Coach"),
            Map.entry("seatPref", "None"),
            Map.entry("outboundFlight", "#{outboundFlight}"),
            Map.entry("advanceDiscount", "0"),
            Map.entry("returnFlight", ""),
            Map.entry("JSFormSubmit", "off"),
            Map.entry("buyFlights.x", "55"),
            Map.entry("buyFlights.y", "6"),
            Map.entry(".cgifields", "saveCC"));
    
    private RequestElements() {
    }
}
