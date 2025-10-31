package com.fame.weatherbot;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            String githubToken = "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"; // Replace with your GitHub token
            WeatherChatServiceImpl service = new WeatherChatServiceImpl(githubToken);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("WeatherChatService", service);

            System.out.println("✅ RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
