package com.facebook.tigon.iface;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.iface.TigonRequestLayers;
import java.util.Map;
import javax.annotation.Nullable;
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

    long addedToMiddlewareSinceEpochMS();

    long connectionTimeoutMS();

    long expectedResponseSizeBytes();

    boolean fallbackToBackupHost();

    @Nullable
    <T> T getLayerInformation(TigonRequestLayers.LayerInfo<T> layerInfo);

    Map<String, String> headers();

    HttpPriority httpPriority();

    long idleTimeoutMS();

    boolean isReliableMediaEnabled();

    String loggingId();

    String method();

    long requestTimeoutMS();

    int requestType();

    boolean retryable();

    long softDeadlineMS();

    int startupStatusOnAdded();

    int tigonPriority();

    String url();
}
