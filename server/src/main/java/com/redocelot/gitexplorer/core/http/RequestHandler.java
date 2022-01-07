package com.redocelot.gitexplorer.core.http;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import org.json.simple.JSONValue;

public final class RequestHandler {

    public static Object get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        Response response = call.execute();

        Object file = JSONValue.parse(response.body().string());
        return file;
    }

}
