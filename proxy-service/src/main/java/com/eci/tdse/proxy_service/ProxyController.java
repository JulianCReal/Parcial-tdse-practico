package com.eci.tdse.proxy_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ProxyController {

    private String getService1() {
        return System.getenv("MATH_SERVICE_1") != null
                ? System.getenv("MATH_SERVICE_1")
                : "http://localhost:8080";
    }

    private String getService2() {
        return System.getenv("MATH_SERVICE_2") != null
                ? System.getenv("MATH_SERVICE_2")
                : "http://localhost:8081";
    }

    @GetMapping("/catalan")
    public String catalan(@RequestParam(value = "number") int number) {
        try {
            return callService(getService1() + "/catalan?number=" + number);
        } catch (IOException e) {
            System.out.println("Service 1 fallando, pasando a service 2");
            try {
                return callService(getService2() + "/catalan?number=" + number);
            } catch (IOException e2){
                return "{\"error\": \"Ambos servicios caídos\"}";            }
        }
    }

    private String callService(String targetUrl) throws IOException {
        URL obj = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setConnectTimeout(3000);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new IOException("Servicio respondió con código: " + responseCode);
        }
    }
}
