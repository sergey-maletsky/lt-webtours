package com.maletsky.pochta.simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

import static com.maletsky.pochta.scenario.Scenario.SCENARIO_BUILDER;
import static com.maletsky.pochta.util.Constants.baseUrl;
import static com.maletsky.pochta.util.Constants.stepTestDurationInSeconds;
import static com.maletsky.pochta.util.Constants.intensity;
import static com.maletsky.pochta.util.Constants.stagesNumber;
import static com.maletsky.pochta.util.Constants.stepTestRampDurationInSeconds;
import static com.maletsky.pochta.util.Constants.stepTestStageDurationInSeconds;
import static io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;

public class StepTest extends Simulation {

    public static final HttpProtocolBuilder httpProtocolBuilder = http.baseUrl(baseUrl)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
            .header("Upgrade-Insecure-Requests", "1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate, br")
            .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:102.0) Gecko/20100101 Firefox/102.0");

    private static final PopulationBuilder stepTestBuilder = SCENARIO_BUILDER.injectOpen(
            // интенсивность на ступень
            incrementUsersPerSec(intensity / stagesNumber)
                    // Количество ступеней
                    .times(stagesNumber)
                    // Длительность полки
                    .eachLevelLasting(stepTestStageDurationInSeconds)
                    // Длительность разгона
                    .separatedByRampsLasting(stepTestRampDurationInSeconds)
                    // Начало нагрузки с
                    .startingFrom(0));

    {
        setUp(stepTestBuilder)
                .protocols(httpProtocolBuilder)
                // общая длительность теста
                .maxDuration(Duration.ofSeconds(stepTestDurationInSeconds));
    }
}
