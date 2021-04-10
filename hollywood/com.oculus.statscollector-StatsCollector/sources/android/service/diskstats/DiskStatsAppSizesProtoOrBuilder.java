package android.service.diskstats;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface DiskStatsAppSizesProtoOrBuilder extends MessageLiteOrBuilder {
    long getAppDataSizeKb();

    long getAppSizeKb();

    long getCacheSizeKb();

    String getPackageName();

    ByteString getPackageNameBytes();

    boolean hasAppDataSizeKb();

    boolean hasAppSizeKb();

    boolean hasCacheSizeKb();

    boolean hasPackageName();
}
