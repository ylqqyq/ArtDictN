package com.example.artdictn;

import android.app.Application;

public class myApp extends Application {
    public JsonService getJsonService() {
        return jsonService;
    }
    public NetworkingService getNetworkingService() {
        return networkingService;
    }
    private JsonService jsonService = new JsonService();
    private NetworkingService networkingService = new NetworkingService();
    private DatabaseManager databaseManager =new DatabaseManager();
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
