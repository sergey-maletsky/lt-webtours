package com.maletsky.pochta.request;

import com.maletsky.pochta.util.ContentParser;
import io.gatling.javaapi.core.ChainBuilder;

import static com.maletsky.pochta.util.Constants.baseUrl;
import static com.maletsky.pochta.util.Constants.passportUrl;
import static com.maletsky.pochta.util.RequestElements.calculateButtonOnMainHeaders;
import static com.maletsky.pochta.util.RequestElements.calculatorWeightHeaders;
import static com.maletsky.pochta.util.RequestElements.comeInLoginButtonHeaders;
import static com.maletsky.pochta.util.RequestElements.loginFormHeaders;
import static com.maletsky.pochta.util.RequestElements.loginParameters;
import static com.maletsky.pochta.util.RequestElements.mainHeaders;
import static com.maletsky.pochta.util.RequestElements.loginHeaders;
import static com.maletsky.pochta.util.RequestElements.suggestionsHeaders;
import static com.maletsky.pochta.util.RequestElements.trackingHeaders;
import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.css;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.group;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public final class Requests {

    private Requests() {
    }

    public static final ChainBuilder rootPage = /*exec(http("root_page").get("/")
            .headers(mainHeaders)
            .check(status().is(200)))

            .*/exec(http("come_in_login")
                    .get("/api/auth/login")
                    .headers(comeInLoginButtonHeaders)
                    .check(status().is(200)))
            .exec(http("passport_authorize")
                    .get(passportUrl + "/oauth2/authorize")
                    .queryParam("client_id", "9BOFgysLFIqmCavfJWQ1tzKkRFsa")
                    .queryParam("scope", "openid%20firstName%20lastName%20middleName%20userName%20birthdate%20email%20phone%20address%20uai")
                    .queryParam("response_type", "code")
                    .queryParam("redirect_uri", "https%3A%2F%2Fwww.portal.test.russianpost.ru%2Fapi%2Fauth%2Fcallback")
                    .queryParam("nonce", "Q_X_fnbgm3nuOoEee9f0s-qxaEcKoH6MPNwTXaqlokk")
                    .queryParam("state", "eyJyZXR1cm5UbyI6Imh0dHBzOi8vd3d3LnBvcnRhbC50ZXN0LnJ1c3NpYW5wb3N0LnJ1LyJ9")
                    .queryParam("partyType", "PHYSICAL")
                    .queryParam("lang", "ru")
                    .queryParam("group", "portal")
                    .headers(loginHeaders)
                    .check(status().is(200)))
            .exec(http("passport_start")
                    .get(passportUrl + "/pc/ext/v1.0/authn/start")
                    .queryParam("at", "MDAzMmxvY2F0aW9uIGh0dHBzOi8vcGFzc3BvcnQudGVzdC5ydXNzaWFucG9zdC5ydQowMDM0aWRlbnRpZmllciAyZDM2YTUxOS04NDRiLTQ0MGMtYTNkYS0zYmMyZWMzMjBiZmEKMDBmN2NpZCBjZG1kdHIgPSBleUpoYkdjaU9pSklVekkxTmlKOS5leUpsZUhBaU9qRTNOREU1TlRBMU5qVXNJbk4xWWlJNklrWnBibWRsY2xCeWFXNTBWRzlyWlc0aUxDSjBiMnRsYmtsa0lqb2lZamN4TnpZM05XRXRZMkV6WWkwME0yRTJMVGxtT1RZdFlqVTFNalpsWkRBNE1qTXpJaXdpWTNKbFlYUnBiMjVFWVhSbElqb2lNVFkzT1RnMU1EVTJOVEl4TUNKOS5pRWZsN0dkejFvSHNjMkZYYXJ1Y3BxUGhyaGlMREJIb0YxWGxldVZkeEdzCjAwMWRjaWQgdGltZSA8IDE2Nzk4NTExNjkxMTUKMDAyZnNpZ25hdHVyZSDXjYgc_IfbYuuowpmAm2B6ZILBNVV14E4IhYDScSEGzgo")
                    .headers(loginHeaders)
                    .check(status().is(200)))
            .exec(http("passport_signin")
                    .get(passportUrl + "/pc/ext/v2.0/form/signIn")
                    .queryParam("flow", "OIDC")
                    .queryParam("restart_uri", "https%3A%2F%2Fpassport.test.russianpost.ru%2Foauth2%2Fauthorize%3Fclient_id%3D9BOFgysLFIqmCavfJWQ1tzKkRFsa%26scope%3Dopenid%2520firstName%2520lastName%2520middleName%2520userName%2520birthdate%2520email%2520phone%2520address%2520uai%26response_type%3Dcode%26redirect_uri%3Dhttps%253A%252F%252Fwww.portal.test.russianpost.ru%252Fapi%252Fauth%252Fcallback%26nonce%3DQ_X_fnbgm3nuOoEee9f0s-qxaEcKoH6MPNwTXaqlokk%26state%3DeyJyZXR1cm5UbyI6Imh0dHBzOi8vd3d3LnBvcnRhbC50ZXN0LnJ1c3NpYW5wb3N0LnJ1LyJ9%26partyType%3DPHYSICAL%26lang%3Dru%26group%3Dportal")
                    .queryParam("lang", "ru_RU")
                    .headers(loginHeaders)
                    .check(status().is(200)))
            .exec();
       /*.exec(session -> {
                log.error("userSessionMy {}",session.getString("userSession"));
                return session;
            })*/

    public static final ChainBuilder loginForm = exec(http("passport_login_form")
            .post(passportUrl + "/pc/ext/v1.0/form/login")
            .headers(loginFormHeaders)
            .formParamMap(loginParameters)
            .check(status().is(200))/*)

            .exec(http("passport_auth_continue")
                    .get(passportUrl + "/pc/ext/v1.0/authorize/continue")
                    .headers(authContinueHeaders)
                    .check(status().is(200)))
            .exec(http("passport_auth_callback")
                    .get(passportUrl + "/api/auth/callback")
                    .queryParam("code", "419d1258-ceab-43fe-b715-e5c0590a1fe8")
                    .queryParam("state", "eyJyZXR1cm5UbyI6Imh0dHBzOi8vd3d3LnBvcnRhbC50ZXN0LnJ1c3NpYW5wb3N0LnJ1LyJ9")
                    .queryParam("session_state", "OUJPRmd5c0xGSXFtQ2F2ZkpXUTF0ektrUkZzYSBodHRwczovL3d3dy5wb3J0YWwudGVzdC5ydXNzaWFucG9zdC5ydSA1VDNDc1pwaGQ0anlhYUd6Ynd0ZFBtY25nMWM1bkVHTzNxUW9Wd01qXzVnLndYUUxmQSBjRlQ2NnctVzd4bz0%")
                    .headers(authCallbackHeaders)
                    .check(status().is(200))*/
            );

    public static final ChainBuilder mainActions = exec(http("suggestions-sender")
            .post("/suggestions/v2/suggestion.find-addresses")
            .headers(suggestionsHeaders)
            .body(StringBody("{\"query\":\"г Москва\",\"limit\":5,\"language\":\"RUSSIAN\",\"mailDirection\":\"ALL\",\"fromBound\":\"CITY\",\"toBound\":\"HOUSE\"}"))
            .check(status().is(200)))
            .exec(http("suggestions-recipient")
                    .post("/suggestions/v2/suggestion.find-addresses")
                    .headers(suggestionsHeaders)
                    .body(StringBody("{\"query\":\"г Санкт-Петербург\",\"limit\":5,\"language\":\"RUSSIAN\",\"mailDirection\":\"ALL\",\"fromBound\":\"COUNTRY\",\"toBound\":\"HOUSE\"}"))
                    .check(status().is(200)))

            .exec(http("calculator-button-on-main-page")
                    .get("/parcels")
                    .queryParam("addressFrom", "0c5b2444-70a0-4932-980c-b4dc0d3f02b5")
                    .queryParam("addressTo", "c2deb16a-0330-4f05-821f-1d09c93331e6")
                    .headers(calculateButtonOnMainHeaders)
                    .check(status().is(200)))

            .exec(http("calculator_weight")
                    .post("/api/calculator/api/v2/calculator.calculate-cost-time-text")
                    .headers(calculatorWeightHeaders)
                    .body(StringBody("{\"costRequestDto\":{\"recipientAddressGuid\":\"c2deb16a-0330-4f05-821f-1d09c93331e6\",\"senderAddressGuid\":\"0c5b2444-70a0-4932-980c-b4dc0d3f02b5\",\"cashOnDeliveryInKopecks\":0,\"declaredValueInKopecks\":0,\"deliveryType\":\"NORMAL\",\"hasCarefullyMark\":false,\"hasDeliveryOnCall\":false,\"hasEuv\":false,\"hasHomeDelivery\":false,\"hasInventory\":false,\"hasNotificationOfDelivery\":false,\"hasRecipientSmsNotification\":false,\"hasSenderSmsNotification\":false,\"hasSmsPackage\":false,\"isOrdered\":false,\"activeMailType\":\"PARCEL\",\"mailType\":\"PARCEL\",\"recipientCountryNumericCode\":\"643\",\"recipientPostalCode\":\"190000\",\"senderPostalCode\":\"101000\",\"transferSumInKopecks\":0,\"weightInGrams\":1000,\"bonusAccountInfo\":null,\"step\":1,\"hasDocumentsDelivery\":false},\"isPayonline\":false,\"recipientAddress\":\"Ð³ Ð¡Ð°Ð½ÐºÑ\u0082-Ð\u009FÐµÑ\u0082ÐµÑ\u0080Ð±Ñ\u0083Ñ\u0080Ð³\",\"recipientCity\":\"Ð¡Ð°Ð½ÐºÑ\u0082-Ð\u009FÐµÑ\u0082ÐµÑ\u0080Ð±Ñ\u0083Ñ\u0080Ð³\",\"recipientCountryName\":\"Ð Ð¾Ñ\u0081Ñ\u0081Ð¸Ð¹Ñ\u0081ÐºÐ°Ñ\u008F Ð¤ÐµÐ´ÐµÑ\u0080Ð°Ñ\u0086Ð¸Ñ\u008F\",\"recipientDistrict\":\"\",\"recipientRegion\":\"Ð\u009CÐ¾Ñ\u0081ÐºÐ²Ð°\",\"senderAddress\":\"Ð³ Ð\u009CÐ¾Ñ\u0081ÐºÐ²Ð°\",\"senderCity\":\"Ð\u009CÐ¾Ñ\u0081ÐºÐ²Ð°\",\"senderCountryName\":\"Ð Ð¾Ñ\u0081Ñ\u0081Ð¸Ð¹Ñ\u0081ÐºÐ°Ñ\u008F Ð¤ÐµÐ´ÐµÑ\u0080Ð°Ñ\u0086Ð¸Ñ\u008F\",\"senderDistrict\":\"\",\"senderRegion\":\"Ð\u009CÐ¾Ñ\u0081ÐºÐ²Ð°\"}"))
                    .check(status().is(200)));

    public static final ChainBuilder trackingPage = exec(http("tracking_page")
            .get("/tracking")
            .queryParam("barcode", "80088546003637")
            .headers(comeInLoginButtonHeaders)
            .check(status().is(200))
            .check(css("#__NEXT_DATA__").saveAs("tracking")))
            .exec(session -> session.set("csrfToken", ContentParser.getCsrfToken(session.getString("tracking"))));

    public static final ChainBuilder trackingActions = /*exec(http("tracking_by_barcodes")
                    .get("/api/tracking/api/v1/trackings/by-barcodes")
                    .queryParam("language", "ru")
                    .headers(trackingHeaders)
                    .header("Referer", baseUrl)
                    .check(status().is(200)))
            .*/exec(http("tracking_barcode")
                    .get("/api/tracking/api/v1/trackings/by-barcodes")
                    .queryParam("language", "ru")
                    .queryParam("track-numbers", "80088546003637")
                    .headers(trackingHeaders)
                    .header("Referer", baseUrl + "/tracking?barcode=80088546003637")
                    .check(status().is(200)))
            .exec(http("tracking_click_on_card")
                    .get("/api/tracking/api/v1/trackings/by-id")
                    .queryParam("language", "ru")
                    .queryParam("track-id", "129529")
                    .headers(trackingHeaders)
                    .header("Referer", baseUrl + "/tracking?barcode=RR000957176RU")
                    .check(status().is(200)));

    public static final ChainBuilder groupRootAndLogin = group("rootAndLogin").on(rootPage).exec(loginForm);
}