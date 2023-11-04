package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FCMSend {
    private static String BASE_URL = "https://fcm.googleapis.com/fcm/send";
    private static String SERVER_KEY = "key=AAAApzTB6z0:APA91bE_DgRO-meZgE86ZyGh1JzWpKcJX8rD_0afk0juvsyvNh0NlfkmDJ_Yg_XhtVVjLV6KorFuthhhs9BIhXNQnnc4D5op5vRaMvOXJkfZZREZFpXbsUEiss55rj6mFYF-I8TMPfNg";

    public static void pushNotification(Context context, String token, String title, String message){
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("to", token);
            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", message);
            jsonObject.put("notification", notification);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("FCM " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", SERVER_KEY);
                    return params;
                }
            };

            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
