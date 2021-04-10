package android.service;

import com.google.protobuf.MessageLiteOrBuilder;

public interface GraphicsStatsJankSummaryProtoOrBuilder extends MessageLiteOrBuilder {
    int getHighInputLatencyCount();

    int getJankyFrames();

    int getMissedDeadlineCount();

    int getMissedVsyncCount();

    int getSlowBitmapUploadCount();

    int getSlowDrawCount();

    int getSlowUiThreadCount();

    int getTotalFrames();

    boolean hasHighInputLatencyCount();

    boolean hasJankyFrames();

    boolean hasMissedDeadlineCount();

    boolean hasMissedVsyncCount();

    boolean hasSlowBitmapUploadCount();

    boolean hasSlowDrawCount();

    boolean hasSlowUiThreadCount();

    boolean hasTotalFrames();
}
