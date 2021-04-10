package android.service;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface GraphicsStatsProtoOrBuilder extends MessageLiteOrBuilder {
    GraphicsStatsHistogramBucketProto getHistogram(int i);

    int getHistogramCount();

    List<GraphicsStatsHistogramBucketProto> getHistogramList();

    String getPackageName();

    ByteString getPackageNameBytes();

    long getStatsEnd();

    long getStatsStart();

    GraphicsStatsJankSummaryProto getSummary();

    long getVersionCode();

    boolean hasPackageName();

    boolean hasStatsEnd();

    boolean hasStatsStart();

    boolean hasSummary();

    boolean hasVersionCode();
}
