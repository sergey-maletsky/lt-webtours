package com.maletsky.pochta.simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import java.time.Duration;

import static com.maletsky.pochta.scenario.Scenario.SCENARIO_BUILDER;
import static com.maletsky.pochta.simulation.StepTest.httpProtocolBuilder;
import static com.maletsky.pochta.util.Constants.calculatedByReliabilityTestIntensity;
import static com.maletsky.pochta.util.Constants.reliabilityTestRampDurationInSeconds;
import static com.maletsky.pochta.util.Constants.reliabilityTestStageDurationInMunites;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;

public class ReliabilityTest extends Simulation {

    private static final PopulationBuilder reliabilityTestBuilder = SCENARIO_BUILDER.injectOpen(
        /*    // разгон - определение максимальной производительности, играя с параметрами
            rampUsersPerSec(1).to(calculatedByReliabilityTestIntensity)
                    .during(reliabilityTestRampDurationInSeconds)
            */
            // разгон
            rampUsersPerSec(0).to(calculatedByReliabilityTestIntensity)
                    .during(reliabilityTestRampDurationInSeconds),
            // полка
            constantUsersPerSec(calculatedByReliabilityTestIntensity)
                    .during(Duration.ofMinutes(reliabilityTestStageDurationInMunites))
    );
    //rampUsers(users).during(duration));

    {
        setUp(reliabilityTestBuilder)
/*                .throttle(
                        reachRps(60).in(Duration.ofSeconds(60)),
                        holdFor(Duration.ofMinutes(3))
                     *//*   jumpToRps(60),
                        holdFor(Duration.ofHours(1))*//*
                )*/
                .protocols(httpProtocolBuilder)
                // длительность теста = разгон + полка
                .maxDuration(Duration.ofSeconds(
                        reliabilityTestRampDurationInSeconds + reliabilityTestStageDurationInMunites * 60L))
        //.assertions(details("finding_flight").responseTime().max().lte(5000))
        //.assertions(details("rootAndLogin").responseTime().max().lte(5000))
        //.assertions(details("rootAndLogin" , "root_page").responseTime().max().lte(5000))
                /*.assertions(global().responseTime().max().lte(5000),
                        global().successfulRequests().percent().gt(2.0))
                .assertions(global().failedRequests().percent().is(70.0))*/;
    }
}
