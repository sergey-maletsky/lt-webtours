package com.maletsky.webtours.request;

import com.maletsky.webtours.util.ContentParser;
import io.gatling.javaapi.core.ChainBuilder;
import java.util.Map;

import static com.maletsky.webtours.util.Constants.baseUrl;
import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.core.CoreDsl.css;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public final class Requests {

    private static final Map<CharSequence, String> loginHeaders = Map.ofEntries(
            Map.entry("Referer", baseUrl + "/cgi-bin/nav.pl?in=home"),
            Map.entry("Origin", baseUrl),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"));
    private static final Map<CharSequence, String> findFlightHeaders = Map.ofEntries(
            Map.entry("Referer", baseUrl + "/cgi-bin/reservations.pl?page=welcome"),
            Map.entry("Origin", baseUrl),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"));
    private static final Map<CharSequence, String> flightHeaders = Map.ofEntries(
            Map.entry("Referer", baseUrl + "/cgi-bin/reservations.pl"),
            Map.entry("Origin", baseUrl),
            Map.entry("Content-Type", "application/x-www-form-urlencoded"));

    private Requests() {
    }

    public static final ChainBuilder rootPage = exec(http("root_page").get("/webtours/"))
            .exec(http("root_page_welcome").get("/cgi-bin/welcome.pl?signOff=true")
                    .header("Referer", baseUrl + "/webtours/")
                    .check(status().is(200)))
            .exec(http("root_page_nav").get("/cgi-bin/nav.pl?in=home")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?signOff=true")
                    .check(status().is(200))
                    .check(css("input[name='userSession']", "value").saveAs("userSession")));
       /*.exec(session -> {
                log.error("userSessionMy {}",session.getString("userSession"));
                return session;
            })*/

    public static final ChainBuilder login = exec(http("login_post").post("/cgi-bin/login.pl")
            .headers(loginHeaders)
            .formParam("userSession", "#{userSession}")
            .formParam("username", "#{login}")
            .formParam("password", "#{password}")
            .formParam("login.x", "58")
            .formParam("login.y", "6")
            .formParam("JSFormSubmit", "off")
            .check(status().is(200)))

            .exec(http("login_get").get("/cgi-bin/login.pl?intro=true")
                    .header("Referer", baseUrl + "/cgi-bin/login.pl")
                    .check(status().is(200)))
            .exec(http("login_nav").get("/cgi-bin/nav.pl?page=menu&in=home")
                    .header("Referer", baseUrl + "/cgi-bin/login.pl")
                    .check(status().is(200))
            );

    public static final ChainBuilder flights = exec(http("flights_welcome").get("/cgi-bin/welcome.pl?page=search")
            .header("Referer", baseUrl + "/cgi-bin/nav.pl?page=menu&in=home")
            .check(status().is(200)))

            .exec(http("flights_nav").get("/cgi-bin/nav.pl?page=menu&in=flights")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?page=search")
                    .check(status().is(200)))
            .exec(http("flights_reservations").get("/cgi-bin/reservations.pl?page=welcome")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?page=search")
                    .check(status().is(200))
                    .check(bodyString().saveAs("cities_body")))
            .exec(session -> {
                final ContentParser parser = ContentParser.parse(session.getString("cities_body"));
                final String departureCity = parser.getDepartureCity();
                return session.setAll(Map.ofEntries(
                        Map.entry("departureCity", departureCity),
                        Map.entry("arrivalCity", parser.getArrivalCity(departureCity)),
                        Map.entry("departureDate", parser.getDepartureDate()),
                        Map.entry("returnDate", parser.getReturnDate())));
            });

    public static final ChainBuilder paymentFlight = exec(http("finding_flight").post("/cgi-bin/reservations.pl")
            .headers(findFlightHeaders)
            .formParam("advanceDiscount", "0")
            .formParam("depart", "#{departureCity}")
            .formParam("departDate", "#{departureDate}")
            .formParam("arrive", "#{arrivalCity}")
            .formParam("returnDate", "#{returnDate}")
            .formParam("numPassengers", "1")
            .formParam("seatPref", "None")
            .formParam("seatType", "Coach")
            .formParam("findFlights.x", "57")
            .formParam("findFlights.y", "3")
            .formParam(".cgifields", "roundtrip")
            .formParam(".cgifields", "seatType")
            .formParam(".cgifields", "seatPref")
            .check(status().is(200))
            .check(bodyString().saveAs("select_flight_body")))

            .exec(session -> {
                final ContentParser parser = ContentParser.parse(session.getString("select_flight_body"));
                return session.set("outboundFlight", parser.getOutboundFlight());
            })
            .exec(http("select_flight").post("/cgi-bin/reservations.pl")
                    .headers(flightHeaders)
                    .formParam("outboundFlight", "#{outboundFlight}")
                    .formParam("numPassengers", "1")
                    .formParam("advanceDiscount", "0")
                    .formParam("seatType", "Coach")
                    .formParam("seatPref", "None")
                    .formParam("findFlights.x", "37")
                    .formParam("findFlights.y", "9")
                    .check(status().is(200))
                    .check(bodyString().saveAs("select_flight_body")))
            .exec(http("payment_flight").post("/cgi-bin/reservations.pl")
                    .headers(flightHeaders)
                    .formParam("firstName", "Sergio")
                    .formParam("lastName", "Maletti")
                    .formParam("address1", "Via S. Mirocle, 7")
                    .formParam("address2", "Milan")
                    .formParam("pass1", "Sergio Maletti")
                    .formParam("creditCard", "")
                    .formParam("expDate", "")
                    .formParam("oldCCOption", "")
                    .formParam("numPassengers", "1")
                    .formParam("seatType", "Coach")
                    .formParam("seatPref", "None")
                    .formParam("outboundFlight", "#{outboundFlight}")
                    .formParam("advanceDiscount", "0")
                    .formParam("returnFlight", "")
                    .formParam("JSFormSubmit", "off")
                    .formParam("buyFlights.x", "55")
                    .formParam("buyFlights.y", "6")
                    .formParam(".cgifields", "saveCC")
                    .check(status().is(200))
                    .check(bodyString().saveAs("select_flight_body")));

    public static final ChainBuilder homePage = exec(http("home_page").get("/cgi-bin/welcome.pl?page=menus")
            .header("Referer", baseUrl + "/cgi-bin/nav.pl?page=menu&in=itinerary")
            .check(status().is(200)))
            .exec(http("home_page_intro").get("/cgi-bin/login.pl?intro=true")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?page=menus")
                    .check(status().is(200)))
            .exec(http("home_page_nav").get("/cgi-bin/nav.pl?page=menu&in=home")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?page=menus")
                    .check(status().is(200)));
}