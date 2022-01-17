package com.redocelot.gitexplorer.components.metrics.repo;


import com.redocelot.gitexplorer.components.metrics.domain.RepoMetrics;
import com.redocelot.gitexplorer.core.http.RequestHandler;

import org.json.simple.JSONObject;

public class HttpMetricsRepo implements MetricsRepo {

    @Override
    public RepoMetrics getMetrics(String owner, String repoName) {
        try {
            Object response = RequestHandler
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
