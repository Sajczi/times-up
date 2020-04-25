package com.example.timesup.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WikiRequestHandler extends ContextWrapper {

    private static final String WIKI_QUERY_URL = "https://pl.wikipedia.org/w/api.php?action=opensearch&search=%s&limit=1&namespace=0&format=json";
    private static final String WIKI_PAGE_URL = "https://pl.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exintro=&titles=%s";
    private RequestQueue requestQueue;

    public WikiRequestHandler(Context base) {
        super(base);
    }

    public void getWikiInformation(String card, TextView view) {
        initRequestQueue();
        processWikiRequests(card, view);
    }

    private void initRequestQueue() {
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork( new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
    }

    private void processWikiRequests(String card, TextView view) {
        requestQueue.add(buildWikiQueryRequest(card, view));
    }

    private JsonArrayRequest buildWikiQueryRequest(String card, TextView view) {
        String url = getWikiQueryUrl(card);
        return  new JsonArrayRequest
                (Request.Method.GET, url, (JSONArray) null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            String url = ((JSONArray) response.get(3)).get(0).toString();
                            String title = url.split("/")[url.split("/").length - 1];
                            requestQueue.add(buildWikiInfoRequest(title, view));
                        } catch (JSONException e) {
                            requestQueue.add(buildWikiInfoRequest(card, view));
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        requestQueue.add(buildWikiInfoRequest(card, view));
                    }});
    }

    private JsonObjectRequest buildWikiInfoRequest(String card, TextView view) {
        String url = getWikiInfoUrl(card);
        return  new JsonObjectRequest
                (Request.Method.GET, url, (JSONObject) null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String key = ((JSONObject)((JSONObject)response.get("query")).get("pages")).keys().next();
                            String text = (String)((JSONObject)((JSONObject)((JSONObject)response.get("query")).get("pages")).get(key)).get("extract");
                            if (text != null && !text.isEmpty()) {
                                view.setText(text.replaceAll("\\<.*?>",""));
                            } else {
                                view.setText("No info to display.");
                        }} catch (JSONException e) {
                            view.setText("No info to display.");
                        }
                    }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            view.setText("No info to display.");
                        }});
    }

    private String getWikiQueryUrl(String card) {
        return String.format(WIKI_QUERY_URL, card);
    }

    private String getWikiInfoUrl(String card) {
        return String.format(WIKI_PAGE_URL, card);
    }
}
