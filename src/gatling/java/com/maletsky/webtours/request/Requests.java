package com.maletsky.webtours.request;

import com.maletsky.webtours.util.ContentParser;
import io.gatling.javaapi.core.ChainBuilder;
import java.util.Map;

import static com.maletsky.webtours.util.Constants.baseUrl;
import static com.maletsky.webtours.util.RequestElements.findFlightHeaders;
import static com.maletsky.webtours.util.RequestElements.findFlightParameters;
import static com.maletsky.webtours.util.RequestElements.flightHeaders;
import static com.maletsky.webtours.util.RequestElements.flightParameters;
import static com.maletsky.webtours.util.RequestElements.loginHeaders;
import static com.maletsky.webtours.util.RequestElements.loginParameters;
import static com.maletsky.webtours.util.RequestElements.paymentFlightParameters;
import static io.gatling.javaapi.core.CoreDsl.bodyString;
import static io.gatling.javaapi.core.CoreDsl.css;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.group;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public final class Requests {

    private Requests() {
    }

    public static final ChainBuilder rootPage = exec(http("root_page").get("/webtours/"))
            .exec(http("root_page_welcome")
                    .get("/cgi-bin/welcome.pl")
                    .queryParam("signOff", "true")
                    .header("Referer", baseUrl + "/webtours/")
                    .check(status().is(200)))
            .exec(http("root_page_nav")
                    .get("/cgi-bin/nav.pl")
                    .queryParam("in", "home")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?signOff=true")
                    .check(status().is(200))
                    .check(css("input[name='userSession']", "value").saveAs("userSession")));
       /*.exec(session -> {
                log.error("userSessionMy {}",session.getString("userSession"));
                return session;
            })*/

    public static final ChainBuilder login = exec(http("login_post").post("/cgi-bin/login.pl")
            .headers(loginHeaders)
            .formParamMap(loginParameters)
            .check(status().is(200)))

            .exec(http("login_get")
                    .get("/cgi-bin/login.pl")
                    .queryParam("intro", "true")
                    .header("Referer", baseUrl + "/cgi-bin/login.pl")
                    .check(status().is(200)))
            .exec(http("login_nav")
                    .get("/cgi-bin/nav.pl")
                    .queryParam("page", "menu")
                    .queryParam("in", "home")
                    .header("Referer", baseUrl + "/cgi-bin/login.pl")
                    .check(status().is(200))
            );

    public static final ChainBuilder flights = exec(http("flights_welcome")
            .get("/cgi-bin/welcome.pl")
            .queryParam("page", "search")
            .header("Referer", baseUrl + "/cgi-bin/nav.pl?page=menu&in=home")
            .check(status().is(200)))

            .exec(http("flights_nav")
                    .get("/cgi-bin/nav.pl")
                    .queryParam("page", "menu")
                    .queryParam("in", "flights")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?page=search")
                    .check(status().is(200)))
            .exec(http("flights_reservations")
                    .get("/cgi-bin/reservations.pl")
                    .queryParam("page", "welcome")
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

    public static final ChainBuilder paymentFlight = exec(http("finding_flight")
            .post("/cgi-bin/reservations.pl")
            .headers(findFlightHeaders)
            .formParamMap(findFlightParameters)
            .check(status().is(200))
            .check(bodyString().saveAs("select_flight_body")))

            .exec(session -> {
                final ContentParser parser = ContentParser.parse(session.getString("select_flight_body"));
                return session.set("outboundFlight", parser.getOutboundFlight());
            })
            .exec(http("select_flight")
                    .post("/cgi-bin/reservations.pl")
                    .headers(flightHeaders)
                    .formParamMap(flightParameters)
                    .check(status().is(200)))

            .exec(http("payment_flight").post("/cgi-bin/reservations.pl")
                    .headers(flightHeaders)
                    .formParamMap(paymentFlightParameters)
                    .check(status().is(200)));

    public static final ChainBuilder homePage = exec(http("home_page")
            .get("/cgi-bin/welcome.pl")
            .queryParam("page", "menus")
            .header("Referer", baseUrl + "/cgi-bin/nav.pl?page=menu&in=itinerary")
            .check(status().is(200)))
            .exec(http("home_page_intro")
                    .get("/cgi-bin/login.pl")
                    .queryParam("intro", "true")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?page=menus")
                    .check(status().is(200)))
            .exec(http("home_page_nav")
                    .get("/cgi-bin/nav.pl")
                    .queryParam("page", "menu")
                    .queryParam("in", "home")
                    .header("Referer", baseUrl + "/cgi-bin/welcome.pl?page=menus")
                    .check(status().is(200)));

    public static final ChainBuilder groupRootAndLogin = group("rootAndLogin").on(rootPage).exec(login);
}