package com.redocelot.vcexplorer.frameworks.http.requesthandlers;

import java.io.IOException;

public interface HttpRequestHandler {
   public Object get(String url) throws IOException;
}
