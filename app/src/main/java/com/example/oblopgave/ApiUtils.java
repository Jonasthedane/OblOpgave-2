package com.example.oblopgave;

public class ApiUtils {
    private ApiUtils() {
    }

    private static final String BASE_URL = "https://anbo-bicyclefinderdb.azurewebsites.net/api/";

    public static BicycleService getBicycleService() {

        return RetroFitClient.getClient(BASE_URL).create(BicycleService.class);
    }



}
