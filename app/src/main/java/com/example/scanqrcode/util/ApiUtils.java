package com.example.scanqrcode.util;

import com.example.scanqrcode.api.APIService;
import com.example.scanqrcode.client.RetrofitClient;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://api-card.herokuapp.com";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
