package com.maletsky.webtours.util;

public final class Constants {

    public static final String baseUrl = "http://webtours.load-test.ru:1080";

    // open model
    public static final double intensity = 80.0;
    public static final double calculatedByReliabilityTestIntensity = 80.0;
    public static final int debugDurationInSeconds = Integer.getInteger("debugDuration", 30);
    public static final int globalMaxDurationInSeconds = Integer.getInteger("globalMaxDuration", 30);
    public static final int stepTestStageDurationInSeconds = Integer.getInteger("stepTestStageDuration", 15);
    public static final int reliabilityTestStageDurationInMunites =
            Integer.getInteger("reliabilityTestStageDuration", 60);
    public static final int stagesNumber = Integer.getInteger("stagesNumber", 80);
    public static final int stepTestRampDurationInSeconds = Integer.getInteger("rampDuration", 2);
    public static final int reliabilityTestRampDurationInSeconds = Integer.getInteger("rampDuration", 30);

    public static final double usersPerSecond = intensity/stagesNumber;

    private Constants() {
    }
}
