package com.redocelot.vcexplorer.components.metrics.adapters;


import com.redocelot.vcexplorer.components.metrics.entities.RepoMetrics;
import com.redocelot.vcexplorer.frameworks.http.requesthandlers.HttpRequestHandler;

import org.json.simple.JSONObject;

public class HttpMetricsDSGateway implements MetricsDSGateway {


    private HttpRequestHandler requestHandler;

    public HttpMetricsDSGateway(HttpRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public RepoMetrics getMetrics(String owner, String repoName) {
        try {
            Object response = this.requestHandler
                    .get("https://api.github.com/repos/" + owner + "/" + repoName + "/community/profile");

           JSONObject responseObj = (JSONObject) response;

           Long healthPercentage = (Long) responseObj.get("health_percentage");
            return new RepoMetrics(healthPercentage);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e);

        }
        return null;
    }
    
}
