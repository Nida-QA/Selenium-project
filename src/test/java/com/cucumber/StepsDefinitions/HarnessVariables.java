package com.cucumber.StepsDefinitions;

import UtilitiesFactory.UtilFactory;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HarnessVariables extends UtilFactory {
    public HarnessVariables() throws Exception {
    }
    protected static JSONObject request,expectedResponse;
    protected static Response response;
    protected static Map responseData = new HashMap<>();
    protected static String TRANSACTION_TIME;
    protected static String RANDOM_TEXT;
    protected static String RANDOM_MSISDN;
    protected static String WALLET_BALANCE;
    protected static String ENTERED_AMOUNT;
    protected static String VALIDATION_TEXT;
    protected static List<String> AGENTS;
    protected static List<String> MERCHANTS;
    protected static String SAVED_BANK;
    protected static String PARTNER_NAME = "MONEYGRAM";
    protected static String IMT_PRODUCT;
    protected static Boolean REWARD_FLAG = false;
    protected static int INVITE_PENDING_COUNT;
}
