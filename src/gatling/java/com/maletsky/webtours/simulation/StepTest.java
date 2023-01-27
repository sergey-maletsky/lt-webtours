package com.maletsky.webtours.simulation;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

import static com.maletsky.webtours.scenario.WebToursScenario.webTours;
import static com.maletsky.webtours.util.Constants.baseUrl;
import static com.maletsky.webtours.util.Constants.stepTestDurationInSeconds;
import static com.maletsky.webtours.util.Constants.intensity;
import static com.maletsky.webtours.util.Constants.stagesNumber;
import static com.maletsky.webtours.util.Constants.stepTestRampDurationInSeconds;
import static com.maletsky.webtours.util.Constants.stepTestStageDurationInSeconds;
import static io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;

public class StepTest extends Simulation {

    public static final HttpProtocolBuilder webToursProtocolBuilder = http.baseUrl(baseUrl)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
            .header("Upgrade-Insecure-Requests", "1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:102.0) Gecko/20100101 Firefox/102.0");

    private static final PopulationBuilder stepTestWebToursBuilder = webTours.injectOpen(
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
        setUp(stepTestWebToursBuilder)
                .protocols(webToursProtocolBuilder)
                // общая длительность теста
                .maxDuration(Duration.ofSeconds(stepTestDurationInSeconds));
    }
}
