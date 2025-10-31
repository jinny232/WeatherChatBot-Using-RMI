package com.fame.weatherbot;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.SwingUtilities;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            WeatherChatService service = (WeatherChatService) registry.lookup("WeatherChatService");

            SwingUtilities.invokeLater(() -> new ChatBotWeatherUI(service).setVisible(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
