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
                : "http://localhost:8010";
    }

    @GetMapping("/catalan")
    public String catalan(@RequestParam(value = "number") int number) {
        try {
            return callService(getService1() + "/calculate?number=" + number);
        } catch (IOException e) {
            System.out.println("Service 1 fallando, pasando a service 2");
            try {
                return callService(getService2() + "/calculate?number=" + number);
            } catch (IOException e2){
                System.out.println("Fallo");
            }
        }
    }

    private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";

    public static String callService(String responseurl) throws IOException {

        URL obj = new URL(responseurl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
    }

}
