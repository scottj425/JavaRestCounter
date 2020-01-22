package com.sjohnson.webserv;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Server {

    public static HashMap<String, Integer> received = new HashMap<>();

    public static void listen() {
        post("/message", (request, response) -> {

            if (!processMessage(request.body())) {
                halt(400, "Request could not be processed as submitted.");
            }

            return new JSONObject().put("count", String.valueOf(getTotalCount())).toString();
        });
    }

    private static boolean processMessage(String payload) {
        Gson gson = new Gson();
        Message message;
        try {
            message = gson.fromJson(payload, Message.class);
        } catch (JsonSyntaxException ex) {
            return false;
        }
        if (message == null || message.id == null || message.message == null || message.id.isEmpty() || message.message.isEmpty()) {
            return false;
        }
        if (!received.containsKey(message.id)) {
            received.put(message.id, countWords(message.message));
        }
        return true;
    }

    private static int getTotalCount() {
        int sum = 0;
        for (int i : received.values()) {
            sum += i;
        }
        return sum;
    }

    private static int countWords(String message) {
        StringTokenizer st = new StringTokenizer(message);
        return st.countTokens();
    }
}
