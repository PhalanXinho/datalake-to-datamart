package org.ulpgc.eii.dacd.feeder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class ApiKeyProvider {
    private static final String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqaWFoYW8yMDAyMTEyOEBnbWFpbC5jb20iLCJqdGkiOiI1NGMxY2RjMi00NzU1LTQzMWMtODk3MC0xNjllZjhmZDAwOGUiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTY3MDk1NzgyMCwidXNlcklkIjoiNTRjMWNkYzItNDc1NS00MzFjLTg5NzAtMTY5ZWY4ZmQwMDhlIiwicm9sZSI6IiJ9.1sItDnbaxCrrl4eCvzRongZQt5B07g-XgYWjOSLAHzw";
    public static String response(String url) throws IOException {
        return Jsoup.connect(url)
                .validateTLSCertificates(false)
                .timeout(60000)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();
    }
}
