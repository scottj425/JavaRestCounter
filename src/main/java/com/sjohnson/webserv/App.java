package com.sjohnson.webserv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.StringTokenizer;

import static spark.Spark.*;

public class App 
{

    public static void main( String[] args )
    {
        Server.listen();
    }
}
