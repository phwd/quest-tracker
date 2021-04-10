package com.facebook.tigon.iface;

import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.Immutable;

@DoNotStrip
@Immutable
public interface TigonRequest {
    @DoNotStrip
    public static final int GENERIC_LARGE_REQUEST = 1;
    @DoNotStrip
    public static final String GET = "GET";
    @DoNotStrip
    public static final String HEAD = "HEAD";
    @DoNotStrip
    public static final int IMAGE = 2;
    @DoNotStrip
    public static final String POST = "POST";
    @DoNotStrip
    public static final int VIDEO = 4;
}
