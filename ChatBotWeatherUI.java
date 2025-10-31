package com.fame.weatherbot;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatBotWeatherUI extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    private final JPanel locationPanel = new JPanel();
    private final JComboBox<String> regionCombo = new JComboBox<>();
    private final JComboBox<String> districtCombo = new JComboBox<>();
    private final JComboBox<String> townshipCombo = new JComboBox<>();
    private final JButton confirmLocationBtn = new JButton("Show Weather");
    private final JButton changeLocationButton = new JButton("Change Location");

    private final JTextArea weatherArea = new JTextArea();
    private final JButton toChatButton = new JButton("Open Chatbot");

    private final JTextArea chatArea = new JTextArea();
    private final JTextField inputField = new JTextField();
    private final JButton sendButton = new JButton("Send");
    private final JButton toWeatherButton = new JButton("Back to Weather");

    private String city = "Mandalay";
    private final String apiKey = "Q2GG4Q8ASFNT6YN54FBN3P2F8";

    private final WeatherChatService service;

    public ChatBotWeatherUI(WeatherChatService service) {
        this.service = service;

        setTitle("Myanmar Weather + Chatbot");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.Y_AXIS));
        locationPanel.add(createFieldPanel("Select Region/State:", regionCombo));
        locationPanel.add(createFieldPanel("Select District:", districtCombo));
        locationPanel.add(createFieldPanel("Select Township:", townshipCombo));

        JPanel btnPanel = new JPanel();
        btnPanel.add(confirmLocationBtn);
        locationPanel.add(btnPanel);
        locationPanel.add(Box.createVerticalStrut(10));

        weatherArea.setEditable(false);
        weatherArea.setLineWrap(true);
        weatherArea.setWrapStyleWord(true);
        JPanel weatherBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        weatherBottom.add(changeLocationButton);
        weatherBottom.add(toChatButton);

        JPanel weatherPanel = new JPanel(new BorderLayout());
        weatherPanel.add(new JScrollPane(weatherArea), BorderLayout.CENTER);
        weatherPanel.add(weatherBottom, BorderLayout.SOUTH);

        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JPanel chatBottom = new JPanel(new BorderLayout());
        chatBottom.add(inputField, BorderLayout.CENTER);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(sendButton);
        buttons.add(toWeatherButton);
        chatBottom.add(buttons, BorderLayout.EAST);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chatPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        chatPanel.add(chatBottom, BorderLayout.SOUTH);

        mainPanel.add(locationPanel, "location");
        mainPanel.add(weatherPanel, "weather");
        mainPanel.add(chatPanel, "chat");
        add(mainPanel);

        for (String region : MyanmarLocations.DISTRICTS_MAP.keySet()) {
            regionCombo.addItem(region);
        }

        regionCombo.addActionListener(e -> {
            districtCombo.removeAllItems();
            String selectedRegion = (String) regionCombo.getSelectedItem();
            if (selectedRegion != null) {
                for (String district : MyanmarLocations.DISTRICTS_MAP.getOrDefault(selectedRegion, new String[0])) {
                    districtCombo.addItem(district);
                }
            }
            townshipCombo.removeAllItems();
        });

        districtCombo.addActionListener(e -> {
            townshipCombo.removeAllItems();
            String selectedDistrict = (String) districtCombo.getSelectedItem();
            if (selectedDistrict != null) {
                for (String township : MyanmarLocations.TOWNSHIPS_MAP.getOrDefault(selectedDistrict, new String[0])) {
                    townshipCombo.addItem(township);
                }
            }
        });

        confirmLocationBtn.addActionListener(e -> {
            String selectedTownship = (String) townshipCombo.getSelectedItem();
            if (selectedTownship != null && !selectedTownship.isEmpty()) {
                city = selectedTownship + ", Myanmar";
                new Thread(() -> {
                    try {
                        String weatherData = service.fetchWeather(city);
                        SwingUtilities.invokeLater(() -> {
                            weatherArea.setText(weatherData);
                            cardLayout.show(mainPanel, "weather");
                        });
                    } catch (Exception ex) {
                        weatherArea.setText("Error: " + ex.getMessage());
                    }
                }).start();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a township.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        changeLocationButton.addActionListener(e -> cardLayout.show(mainPanel, "location"));
        toChatButton.addActionListener(e -> cardLayout.show(mainPanel, "chat"));
        toWeatherButton.addActionListener(e -> cardLayout.show(mainPanel, "weather"));

        sendButton.addActionListener(e -> handleChatSend());
        inputField.addActionListener(e -> handleChatSend());

        regionCombo.setSelectedIndex(0);
        cardLayout.show(mainPanel, "location");
    }

    private JPanel createFieldPanel(String labelText, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(150, 25));
        comboBox.setPreferredSize(new Dimension(200, 25));
        panel.add(label);
        panel.add(comboBox);
        return panel;
    }

    private void handleChatSend() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;
        appendChat("You: " + userText);
        inputField.setText("");

        new Thread(() -> {
            try {
                String reply = service.askAI(userText);
                SwingUtilities.invokeLater(() -> appendChat("Bot: " + reply));
            } catch (Exception e) {
                appendChat("Bot: [Error] " + e.getMessage());
            }
        }).start();
    }

    private void appendChat(String text) {
        chatArea.append(text + "\n\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
}
