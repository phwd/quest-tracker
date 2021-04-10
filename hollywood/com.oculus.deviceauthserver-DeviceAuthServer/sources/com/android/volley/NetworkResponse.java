package com.android.volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NetworkResponse {
    public final List<Header> allHeaders;
    public final byte[] data;
    public final Map<String, String> headers;
    public final long networkTimeMs;
    public final boolean notModified;
    public final int statusCode;

    @Deprecated
    public NetworkResponse(int statusCode2, byte[] data2, Map<String, String> headers2, boolean notModified2, long networkTimeMs2) {
        this(statusCode2, data2, headers2, toAllHeaderList(headers2), notModified2, networkTimeMs2);
    }

    public NetworkResponse(int statusCode2, byte[] data2, boolean notModified2, long networkTimeMs2, List<Header> allHeaders2) {
        this(statusCode2, data2, toHeaderMap(allHeaders2), allHeaders2, notModified2, networkTimeMs2);
    }

    @Deprecated
    public NetworkResponse(int statusCode2, byte[] data2, Map<String, String> headers2, boolean notModified2) {
        this(statusCode2, data2, headers2, notModified2, 0);
    }

    public NetworkResponse(byte[] data2) {
        this(200, data2, false, 0L, (List<Header>) Collections.emptyList());
    }

    @Deprecated
    public NetworkResponse(byte[] data2, Map<String, String> headers2) {
        this(200, data2, headers2, false, 0L);
    }

    private NetworkResponse(int statusCode2, byte[] data2, Map<String, String> headers2, List<Header> allHeaders2, boolean notModified2, long networkTimeMs2) {
        this.statusCode = statusCode2;
        this.data = data2;
        this.headers = headers2;
        if (allHeaders2 == null) {
            this.allHeaders = null;
        } else {
            this.allHeaders = Collections.unmodifiableList(allHeaders2);
        }
        this.notModified = notModified2;
        this.networkTimeMs = networkTimeMs2;
    }

    private static Map<String, String> toHeaderMap(List<Header> allHeaders2) {
        if (allHeaders2 == null) {
            return null;
        }
        if (allHeaders2.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, String> headers2 = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Header header : allHeaders2) {
            headers2.put(header.getName(), header.getValue());
        }
        return headers2;
    }

    private static List<Header> toAllHeaderList(Map<String, String> headers2) {
        if (headers2 == null) {
            return null;
        }
        if (headers2.isEmpty()) {
            return Collections.emptyList();
        }
        List<Header> allHeaders2 = new ArrayList<>(headers2.size());
        for (Map.Entry<String, String> header : headers2.entrySet()) {
            allHeaders2.add(new Header(header.getKey(), header.getValue()));
        }
        return allHeaders2;
    }
}
