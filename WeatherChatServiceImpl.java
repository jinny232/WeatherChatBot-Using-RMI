package com.fame.weatherbot;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.*;
import com.azure.core.credential.AzureKeyCredential;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class WeatherChatServiceImpl extends UnicastRemoteObject implements WeatherChatService {
    private final ChatCompletionsClient aiClient;
    private final String model = "openai/gpt-4.1";
    private final String weatherApiKey = "Q2GG4Q8ASFNT6YN54FBN3P2F8";

    protected WeatherChatServiceImpl(String githubToken) throws Exception {
        super();
        aiClient = new ChatCompletionsClientBuilder()
                .endpoint("https://models.github.ai/inference")
                .credential(new AzureKeyCredential(githubToken))
                .buildClient();
    }

    @Override
    public String fetchWeather(String city) {
        try {
            LocalDate today = LocalDate.now(ZoneId.of("Asia/Yangon"));
            LocalDate start = today.minusDays(2);
            LocalDate end = today.plusDays(2);

            String url = String.format(
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s/%s/%s?unitGroup=metric&include=days&key=%s",
                city.replace(" ", "%20"), start, end, weatherApiKey
            );

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder jsonBuilder = new StringBuilder();
                br.lines().forEach(jsonBuilder::append);
                JSONObject data = new JSONObject(jsonBuilder.toString());

                JSONArray days = data.getJSONArray("days");
                StringBuilder result = new StringBuilder("Weather in " + data.getString("resolvedAddress") + ":\n\n");

                for (int i = 0; i < days.length(); i++) {
                    JSONObject d = days.getJSONObject(i);
                    result.append(d.getString("datetime")).append(":\n");
                    result.append("  Condition: ").append(d.getString("conditions")).append("\n");
                    result.append("  Temp: Min ").append(d.getDouble("tempmin")).append("°C, Max ")
                          .append(d.getDouble("tempmax")).append("°C\n");
                    result.append("  Humidity: ").append(d.getInt("humidity")).append("%\n\n");
                }

                return result.toString();
            }
        } catch (Exception e) {
            return "Error fetching weather: " + e.getMessage();
        }
    }

    @Override
    public String askAI(String prompt) {
        try {
            List<ChatRequestMessage> messages = Arrays.asList(
                new ChatRequestSystemMessage("You are a helpful and friendly assistant."),
                new ChatRequestUserMessage(prompt)
            );

            ChatCompletionsOptions options = new ChatCompletionsOptions(messages).setModel(model);
            ChatCompletions result = aiClient.complete(options);
            return result.getChoices().get(0).getMessage().getContent();
        } catch (Exception ex) {
            return "Error from AI: " + ex.getMessage();
        }
    }
}
