package com.build.myapplication.Service;

public class APIService {
    private static String base_url = "https://jestermusic.tk/Server/";

    public static DataService getService() {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
