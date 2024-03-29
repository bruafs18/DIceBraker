package com.example.cs18afs.v3;

import android.os.StrictMode;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class SlackUtils {

    private static String slackWebhookUrl = "https://hooks.slack.com/services/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";



    public static void sendMessage(SlackMessage message) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            //CloseableHttpClient client = HttpClients.createDefault();

            URL url = new URL(slackWebhookUrl);
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestProperty("Content-type", "application/json");
            
            client.setRequestProperty( "Accept", "*/*" );
            //client.setRequestProperty("authorization", "Bearer " + "xoxp-763082602722-763149615602-815829401648-08f64702930096b280039ee517fe5806");
            client.setRequestMethod("POST");
            client.setDoInput(true);
            client.setDoOutput(true);

            OutputStreamWriter request = new OutputStreamWriter(client.getOutputStream());
            String json = "{\"text\":\"test\"}";
            request.write(json);
            request.flush();
            request.close();
        }
        catch(Exception e)
        {
            throw e;
        }

    }

}