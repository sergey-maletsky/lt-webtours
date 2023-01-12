package com.maletsky.webtours.simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

import static com.maletsky.webtours.scenario.WebToursScenario.webTours;
import static com.maletsky.webtours.util.Constants.baseUrl;
import static com.maletsky.webtours.util.Constants.duration;
import static com.maletsky.webtours.util.Constants.users;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.holdFor;
import static io.gatling.javaapi.core.CoreDsl.jumpToRps;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.reachRps;
import static io.gatling.javaapi.http.HttpDsl.http;

public class WebToursSimulation extends Simulation {

    final HttpProtocolBuilder webToursProtocolBuilder = http.baseUrl(baseUrl)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
            .header("Upgrade-Insecure-Requests", "1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:102.0) Gecko/20100101 Firefox/102.0");

    private static final PopulationBuilder webToursBuilder = webTours.injectOpen(
            constantUsersPerSec(1).during(duration));
            //rampUsers(users).during(duration));
    //rampUsersPerSec(0).to(100).during(duration));

    {
        setUp(webToursBuilder)
/*                .throttle(
                        reachRps(100).in(10),
                        holdFor(Duration.ofMinutes(20)),
                        jumpToRps(80),
                        holdFor(Duration.ofHours(1))
                )*/
                .protocols(webToursProtocolBuilder)
                .assertions(global().responseTime().max().lte(5000))
                .assertions(global().failedRequests().percent().is(70.0));
    }
}
