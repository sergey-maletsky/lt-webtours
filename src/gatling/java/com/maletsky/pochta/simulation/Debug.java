package com.maletsky.pochta.simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import java.time.Duration;

import static com.maletsky.pochta.scenario.Scenario.SCENARIO_BUILDER;
import static com.maletsky.pochta.simulation.StepTest.httpProtocolBuilder;
import static com.maletsky.pochta.util.Constants.debugDurationInSeconds;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;

public class Debug extends Simulation {

    private static final PopulationBuilder debugBuilder = SCENARIO_BUILDER.injectOpen(atOnceUsers(1));

    {
        setUp(debugBuilder)
                .protocols(httpProtocolBuilder)
                .maxDuration(Duration.ofSeconds(debugDurationInSeconds));
    }
}
