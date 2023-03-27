package com.maletsky.pochta.scenario;

import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;


import static com.maletsky.pochta.request.Requests.loginForm;
import static com.maletsky.pochta.request.Requests.mainActions;
import static com.maletsky.pochta.request.Requests.rootPage;
import static com.maletsky.pochta.request.Requests.trackingActions;
import static com.maletsky.pochta.request.Requests.trackingPage;
import static com.maletsky.pochta.util.Constants.scenarioName;
import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public final class Scenario {

    private Scenario() {
    }

    private static final FeederBuilder<String> authFeeder = csv("auth.csv").random();
    public static final ScenarioBuilder SCENARIO_BUILDER = scenario(scenarioName)
            .feed(authFeeder)
            .exec(rootPage, loginForm, mainActions, trackingPage, trackingActions);

    //suggestions-sender - 131
}
