package com.fame.weatherbot;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WeatherChatService extends Remote {
    String fetchWeather(String city) throws RemoteException;
    String askAI(String prompt) throws RemoteException;
}
