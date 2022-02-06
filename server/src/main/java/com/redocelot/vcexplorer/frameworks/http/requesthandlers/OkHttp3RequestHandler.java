package com.redocelot.vcexplorer.frameworks.http.requesthandlers;

import okhttp3.Call;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Request.Builder;

import java.io.IOException;

import org.json.simple.JSONValue;

import io.github.cdimascio.dotenv.Dotenv;

public final class OkHttp3RequestHandler implements HttpRequestHandler {

    public Object get(String url) throws IOException {
        Dotenv dotenv = Dotenv.configure()
        .ignoreIfMissing()
        .load();


        String token = dotenv.get("GIT_ACCESS_TOKEN");
        
        Builder builder = new Request.Builder()
                .method("GET", null)
                .url(url);
                

        if(token != null && !token.isEmpty()) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        Request request = builder.build();

        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        Response response = call.execute();

        Object file = JSONValue.parse(response.body().string());
        return file;
    }

}
