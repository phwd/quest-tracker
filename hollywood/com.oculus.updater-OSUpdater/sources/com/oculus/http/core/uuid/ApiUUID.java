package com.oculus.http.core.uuid;

import java.util.List;
import java.util.UUID;
import retrofit.client.Header;

public class ApiUUID {
    public static String gen() {
        return UUID.randomUUID().toString();
    }

    public static String getFromHeaders(List<Header> list) {
        for (Header header : list) {
            if ("oculus-request-id".equals(header.getName())) {
                return header.getValue();
            }
        }
        return null;
    }
}
