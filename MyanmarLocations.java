package com.fame.weatherbot;
import java.util.Map;

public class MyanmarLocations {
    public static final Map<String, String[]> DISTRICTS_MAP = Map.ofEntries(
        // 1. Yangon Region
        Map.entry("Yangon Region", new String[]{"East Yangon District", "West Yangon District", "South Yangon District", "North Yangon District"}),

        // 2. Mandalay Region
        Map.entry("Mandalay Region", new String[]{"Mandalay District", "Pyin Oo Lwin District", "Myingyan District"}),

        // 3. Naypyidaw Union Territory
        Map.entry("Naypyidaw Union Territory", new String[]{"Dekkhina District", "Ottara District"}),

        // 4. Bago Region
        Map.entry("Bago Region", new String[]{"Bago District", "Taungoo District"}),

        // 5. Ayeyarwady Region
        Map.entry("Ayeyarwady Region", new String[]{"Pathein District", "Hinthada District"}),

        // 6. Magway Region
        Map.entry("Magway Region", new String[]{"Magway District", "Pakokku District"}),

        // 7. Sagaing Region
        Map.entry("Sagaing Region", new String[]{"Sagaing District", "Shwebo District"}),

        // 8. Tanintharyi Region
        Map.entry("Tanintharyi Region", new String[]{"Dawei District", "Myeik District"}),

        // 9. Chin State
        Map.entry("Chin State", new String[]{"Hakha District", "Falam District"}),

        // 10. Kachin State
        Map.entry("Kachin State", new String[]{"Myitkyina District", "Bhamo District"}),

        // 11. Kayah State
        Map.entry("Kayah State", new String[]{"Loikaw District"}),

        // 12. Kayin State
        Map.entry("Kayin State", new String[]{"Hpa-an District", "Myawaddy District"}),

        // 13. Mon State
        Map.entry("Mon State", new String[]{"Mawlamyine District", "Thaton District"}),

        // 14. Shan State
        Map.entry("Shan State", new String[]{"Taunggyi District", "Lashio District", "Kengtung District"})
    );

    public static final Map<String, String[]> TOWNSHIPS_MAP = Map.ofEntries(
        // Yangon Region
        Map.entry("East Yangon District", new String[]{"East Dagon", "North Dagon", "South Dagon", "Thaketa"}),
        Map.entry("West Yangon District", new String[]{"Hlaingthaya", "Kyimyindaing", "Mayangone", "Pazundaung"}),
        Map.entry("South Yangon District", new String[]{"Dala", "Seikkyi Kanaungto", "Thanlyin"}),
        Map.entry("North Yangon District", new String[]{"Insein", "Hlegu", "Shwepyitha"}),

        // Mandalay Region
        Map.entry("Mandalay District", new String[]{"Aungmyethazan", "Chanayethazan", "Maha Aungmye", "Pyigyidagun"}),
        Map.entry("Pyin Oo Lwin District", new String[]{"Pyin Oo Lwin", "Madaya"}),
        Map.entry("Myingyan District", new String[]{"Myingyan", "Taungtha"}),

        // Naypyidaw
        Map.entry("Dekkhina District", new String[]{"Zeyathiri", "Tatkon"}),
        Map.entry("Ottara District", new String[]{"Ottara", "Pobbathiri"}),

        // Bago Region
        Map.entry("Bago District", new String[]{"Bago", "Phyu", "Shwedaung"}),
        Map.entry("Taungoo District", new String[]{"Taungoo", "Kyaukkyi", "Yedashe"}),

        // Ayeyarwady Region
        Map.entry("Pathein District", new String[]{"Pathein", "Ngapudaw"}),
        Map.entry("Hinthada District", new String[]{"Hinthada", "Kyangin"}),

        // Magway Region
        Map.entry("Magway District", new String[]{"Magway", "Minbu"}),
        Map.entry("Pakokku District", new String[]{"Pakokku", "Yesagyo"}),

        // Sagaing Region
        Map.entry("Sagaing District", new String[]{"Sagaing", "Myinmu"}),
        Map.entry("Shwebo District", new String[]{"Shwebo", "Khin-U"}),

        // Tanintharyi Region
        Map.entry("Dawei District", new String[]{"Dawei", "Thayetchaung"}),
        Map.entry("Myeik District", new String[]{"Myeik", "Kyunsu"}),

        // Chin State
        Map.entry("Hakha District", new String[]{"Hakha", "Thantlang"}),
        Map.entry("Falam District", new String[]{"Falam", "Tonzang"}),

        // Kachin State
        Map.entry("Myitkyina District", new String[]{"Myitkyina", "Waingmaw"}),
        Map.entry("Bhamo District", new String[]{"Bhamo", "Shwegu"}),

        // Kayah State
        Map.entry("Loikaw District", new String[]{"Loikaw", "Demoso"}),

        // Kayin State
        Map.entry("Hpa-an District", new String[]{"Hpa-an", "Hlaingbwe"}),
        Map.entry("Myawaddy District", new String[]{"Myawaddy", "Kawkareik"}),

        // Mon State
        Map.entry("Mawlamyine District", new String[]{"Mawlamyine", "Chaungzon"}),
        Map.entry("Thaton District", new String[]{"Thaton", "Paung"}),

        // Shan State
        Map.entry("Taunggyi District", new String[]{"Taunggyi", "Nyaungshwe"}),
        Map.entry("Lashio District", new String[]{"Lashio", "Hseni"}),
        Map.entry("Kengtung District", new String[]{"Kengtung", "Tachileik"})
    );
}
