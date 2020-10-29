package com.example.oblopgave;

public class ApiUtils {
    private ApiUtils() {
    }

    private static final String BASE_URL = "https://anbo-bicyclefinder.azurewebsites.net/api/bicycles/";

    public static BicycleService getBicycleService() {

        return RetroFitClient.getClient(BASE_URL).create(BicycleService.class);
    }
}
