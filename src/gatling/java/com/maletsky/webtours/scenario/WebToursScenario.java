package com.maletsky.webtours.scenario;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static com.maletsky.webtours.request.Requests.flights;
import static com.maletsky.webtours.request.Requests.homePage;
import static com.maletsky.webtours.request.Requests.login;
import static com.maletsky.webtours.request.Requests.paymentFlight;
import static com.maletsky.webtours.request.Requests.rootPage;
import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public final class WebToursScenario {

    private WebToursScenario() {
    }

    private static final FeederBuilder<String> authFeeder = csv("auth.csv").random();
    public static final ScenarioBuilder webTours = scenario("WebTours")
            .feed(authFeeder)
            .exec(rootPage, login, flights, paymentFlight, homePage);
}
