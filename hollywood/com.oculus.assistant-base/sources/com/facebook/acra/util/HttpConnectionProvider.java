package com.facebook.acra.util;

import java.net.HttpURLConnection;
import java.net.URL;

public interface HttpConnectionProvider {
    HttpURLConnection getConnection(URL url);
}
