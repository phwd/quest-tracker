package android.service;

import com.google.protobuf.MessageLiteOrBuilder;

public interface GraphicsStatsHistogramBucketProtoOrBuilder extends MessageLiteOrBuilder {
    int getFrameCount();

    int getRenderMillis();

    boolean hasFrameCount();

    boolean hasRenderMillis();
}
