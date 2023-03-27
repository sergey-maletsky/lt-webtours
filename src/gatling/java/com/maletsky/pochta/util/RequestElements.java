package com.maletsky.pochta.util;

import java.util.Map;

import static com.maletsky.pochta.util.Constants.baseUrl;
import static com.maletsky.pochta.util.Constants.passportUrl;

public final class RequestElements {

    // headers
    public static final Map<CharSequence, String> mainHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "navigate"),
            Map.entry("Sec-Fetch-Site", "none"),
            Map.entry("Sec-Fetch-User", "?1"),
            Map.entry("Sec-Fetch-Dest", "document")
    );

    public static final Map<CharSequence, String> comeInLoginButtonHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "navigate"),
            Map.entry("Referer", baseUrl),
            Map.entry("Sec-Fetch-Site", "same-origin"),
            Map.entry("Sec-Fetch-User", "?1"),
            Map.entry("Sec-Fetch-Dest", "document")
    );

    public static final Map<CharSequence, String> loginHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "navigate"),
            Map.entry("Referer", baseUrl),
            Map.entry("Sec-Fetch-Site", "same-site"),
            Map.entry("Sec-Fetch-User", "?1"),
            Map.entry("Sec-Fetch-Dest", "document")
    );

    public static final Map<CharSequence, String> loginFormHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "navigate"),
            Map.entry("Referer", passportUrl + "/pc/ext/v2.0/form/signIn?flow=OIDC&restart_uri=https%3A%2F%2Fpassport.test.russianpost.ru%2Foauth2%2Fauthorize%3Fclient_id%3D9BOFgysLFIqmCavfJWQ1tzKkRFsa%26scope%3Dopenid%2520firstName%2520lastName%2520middleName%2520userName%2520birthdate%2520email%2520phone%2520address%2520uai%26response_type%3Dcode%26redirect_uri%3Dhttps%253A%252F%252Fwww.portal.test.russianpost.ru%252Fapi%252Fauth%252Fcallback%26nonce%3DQ_X_fnbgm3nuOoEee9f0s-qxaEcKoH6MPNwTXaqlokk%26state%3DeyJyZXR1cm5UbyI6Imh0dHBzOi8vd3d3LnBvcnRhbC50ZXN0LnJ1c3NpYW5wb3N0LnJ1LyJ9%26partyType%3DPHYSICAL%26lang%3Dru%26group%3Dportal&lang=ru_RU"),
            Map.entry("Sec-Fetch-Site", "same-origin"),
            Map.entry("Origin", passportUrl),
            Map.entry("Sec-Fetch-User", "?1"),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"),
            Map.entry("Sec-Fetch-Dest", "document"));

    public static final Map<CharSequence, String> authContinueHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "navigate"),
            Map.entry("Referer", passportUrl + "/pc/ext/v2.0/form/signIn?flow=OIDC&restart_uri=https%3A%2F%2Fpassport.test.russianpost.ru%2Foauth2%2Fauthorize%3Fclient_id%3D9BOFgysLFIqmCavfJWQ1tzKkRFsa%26scope%3Dopenid%2520firstName%2520lastName%2520middleName%2520userName%2520birthdate%2520email%2520phone%2520address%2520uai%26response_type%3Dcode%26redirect_uri%3Dhttps%253A%252F%252Fwww.portal.test.russianpost.ru%252Fapi%252Fauth%252Fcallback%26nonce%3DQ_X_fnbgm3nuOoEee9f0s-qxaEcKoH6MPNwTXaqlokk%26state%3DeyJyZXR1cm5UbyI6Imh0dHBzOi8vd3d3LnBvcnRhbC50ZXN0LnJ1c3NpYW5wb3N0LnJ1LyJ9%26partyType%3DPHYSICAL%26lang%3Dru%26group%3Dportal&lang=ru_RU"),
            Map.entry("Sec-Fetch-Site", "same-origin"),
            Map.entry("Sec-Fetch-User", "?1"),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"),
            Map.entry("Sec-Fetch-Dest", "document"));

    public static final Map<CharSequence, String> authCallbackHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "navigate"),
            Map.entry("Referer", passportUrl),
            Map.entry("Sec-Fetch-Site", "same-site"),
            Map.entry("Sec-Fetch-User", "?1"),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"),
            Map.entry("Sec-Fetch-Dest", "document"));

    public static final Map<CharSequence, String> trackingHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "cors"),
            Map.entry("Sec-Fetch-Site", "same-origin"),
            Map.entry("Accept", "application/json"),
            Map.entry("csrf-token", "#{csrfToken}"),
            Map.entry("Sec-Fetch-Dest", "empty"));

    public static final Map<CharSequence, String> suggestionsHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "cors"),
            Map.entry("Referer", baseUrl),
            Map.entry("Sec-Fetch-Site", "same-origin"),
            Map.entry("Origin", baseUrl),
            Map.entry("Accept", "application/json"),
            Map.entry("Content-Type", "application/json"),
            Map.entry("Sec-Fetch-Dest", "empty"));

    public static final Map<CharSequence, String> calculateButtonOnMainHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "navigate"),
            Map.entry("Referer", baseUrl),
            Map.entry("Sec-Fetch-Site", "same-origin"),
            Map.entry("Sec-Fetch-User", "?1"),
            Map.entry("Sec-Fetch-Dest", "document"));

    public static final Map<CharSequence, String> calculatorWeightHeaders = Map.ofEntries(
            Map.entry("Sec-Fetch-Mode", "cors"),
            Map.entry("Referer", baseUrl + "/parcels?addressFrom=0c5b2444-70a0-4932-980c-b4dc0d3f02b5&addressTo=c2deb16a-0330-4f05-821f-1d09c93331e6"),
            Map.entry("Sec-Fetch-Site", "same-origin"),
            Map.entry("Origin", baseUrl),
            Map.entry("Accept", "application/json"),
            Map.entry("Content-Type", "application/json"),
            Map.entry("Sec-Fetch-Dest", "empty"));

    // form parameters
    public static final Map<String, Object> loginParameters = Map.ofEntries(
            Map.entry("login", "#{login}"),
            Map.entry("password", "#{password}"),
            Map.entry("restartUrl", "https%3A%2F%2Fpassport.test.russianpost.ru%2Foauth2%2Fauthorize%3Fclient_id%3D9BOFgysLFIqmCavfJWQ1tzKkRFsa%26scope%3Dopenid%2520firstName%2520lastName%2520middleName%2520userName%2520birthdate%2520email%2520phone%2520address%2520uai%26response_type%3Dcode%26redirect_uri%3Dhttps%253A%252F%252Fwww.portal.test.russianpost.ru%252Fapi%252Fauth%252Fcallback%26nonce%3DQ_X_fnbgm3nuOoEee9f0s-qxaEcKoH6MPNwTXaqlokk%26state%3DeyJyZXR1cm5UbyI6Imh0dHBzOi8vd3d3LnBvcnRhbC50ZXN0LnJ1c3NpYW5wb3N0LnJ1LyJ9%26partyType%3DPHYSICAL%26lang%3Dru%26group%3Dportal"),
            Map.entry("fp", "N4IgJgZgDiBcDaoDWBTAnnEBXAzigTgIIDmKAdgC4gA0IAbgIYA2WKmAsgPYBeAlk0wYB6AKwA6AAwACABQANAIwKA3FICqAIyyUsqgDK8yWAB5TjADgBsAfUsAWVfjqwFEgEySAlFIDiKAMZInEJuEq5hYVIAYrz4KBCcxkKuHhIgAL7UyOiYAO4oGmD4vHQENPTMrHAQzHiZ2RiwIIJkxFgMpOWMLGxN5AC0agDKGVkgqI0g"),
            Map.entry("jast", "3565e545ec1c6416e3b9368739eb4d85fb68edf5"),
            Map.entry("flow", "OIDC"),
            Map.entry("jca", "-139050"),
            Map.entry("jci", "3d51ce2f-a751-4fff-8207-c2031ddba00b"),
            Map.entry("username", "#{login}"),
            Map.entry("userpassword", "#{password}"));
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
