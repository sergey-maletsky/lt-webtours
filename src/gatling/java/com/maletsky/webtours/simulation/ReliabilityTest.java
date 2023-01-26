package com.maletsky.webtours.simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import java.time.Duration;

import static com.maletsky.webtours.scenario.WebToursScenario.webTours;
import static com.maletsky.webtours.simulation.StepTest.webToursProtocolBuilder;
import static com.maletsky.webtours.util.Constants.calculatedByReliabilityTestIntensity;
import static com.maletsky.webtours.util.Constants.reliabilityTestRampDurationInSeconds;
import static com.maletsky.webtours.util.Constants.reliabilityTestStageDurationInMunites;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;

public class ReliabilityTest extends Simulation {

    private static final PopulationBuilder reliabilityTestWebToursBuilder = webTours.injectOpen(
            // разгон
            rampUsersPerSec(0).to(calculatedByReliabilityTestIntensity)
                    .during(reliabilityTestRampDurationInSeconds),
            // полка
            constantUsersPerSec(calculatedByReliabilityTestIntensity)
                    .during(Duration.ofMinutes(reliabilityTestStageDurationInMunites))
    );
    //rampUsers(users).during(duration));

    {
        setUp(reliabilityTestWebToursBuilder)
/*                .throttle(
                        reachRps(100).in(10),
                        holdFor(Duration.ofMinutes(20)),
                        jumpToRps(80),
                        holdFor(Duration.ofHours(1))
                )*/
                .protocols(webToursProtocolBuilder)
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
