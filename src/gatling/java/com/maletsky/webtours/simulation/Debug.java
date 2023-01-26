package com.maletsky.webtours.simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import java.time.Duration;

import static com.maletsky.webtours.scenario.WebToursScenario.webTours;
import static com.maletsky.webtours.simulation.StepTest.webToursProtocolBuilder;
import static com.maletsky.webtours.util.Constants.debugDurationInSeconds;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;

public class Debug extends Simulation {

    private static final PopulationBuilder debugWebToursBuilder = webTours.injectOpen(atOnceUsers(1));

    {
        setUp(debugWebToursBuilder)
                .protocols(webToursProtocolBuilder)
                .maxDuration(Duration.ofSeconds(debugDurationInSeconds));
    }
}
