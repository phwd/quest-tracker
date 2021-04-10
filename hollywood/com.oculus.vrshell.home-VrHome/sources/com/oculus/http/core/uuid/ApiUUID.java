package com.oculus.http.core.uuid;

import java.util.List;
import java.util.UUID;
import retrofit.client.Header;

public class ApiUUID {
    public static final String HEADER_NAME = "oculus-request-id";

    public static String gen() {
        return UUID.randomUUID().toString();
    }

    public static String getFromHeaders(List<Header> headers) {
        for (Header header : headers) {
            if (HEADER_NAME.equals(header.getName())) {
                return header.getValue();
            }
        }
        return null;
    }
}
