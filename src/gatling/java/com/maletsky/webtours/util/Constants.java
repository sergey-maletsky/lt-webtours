package com.maletsky.webtours.util;

public final class Constants {

    public static final String baseUrl = "http://webtours.load-test.ru:1080";
    public static final int users = Integer.getInteger("users", 100);
    public static final int duration = Integer.getInteger("duration", 30);
    public static final int rampup = Integer.getInteger("rampup", 1);

    private Constants() {
    }
}
