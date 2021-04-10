package com.facebook.tigon.iface;

import java.util.Map;

public interface TigonRequest {
    public static final int GENERIC_LARGE_REQUEST = 1;
    public static final String GET = "GET";
    public static final String HEAD = "HEAD";
    public static final int IMAGE = 2;
    public static final String POST = "POST";
    public static final int VIDEO = 4;

    Map headers();

    String method();

    String url();
}
