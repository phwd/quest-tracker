package android.service.diskstats;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface DiskStatsCachedValuesProtoOrBuilder extends MessageLiteOrBuilder {
    long getAggAppsCacheSizeKb();

    long getAggAppsDataSizeKb();

    long getAggAppsSizeKb();

    DiskStatsAppSizesProto getAppSizes(int i);

    int getAppSizesCount();

    List<DiskStatsAppSizesProto> getAppSizesList();

    long getAudioSizeKb();

    long getDownloadsSizeKb();

    long getOtherSizeKb();

    long getPhotosSizeKb();

    long getSystemSizeKb();

    long getVideosSizeKb();

    boolean hasAggAppsCacheSizeKb();

    boolean hasAggAppsDataSizeKb();

    boolean hasAggAppsSizeKb();

    boolean hasAudioSizeKb();

    boolean hasDownloadsSizeKb();

    boolean hasOtherSizeKb();

    boolean hasPhotosSizeKb();

    boolean hasSystemSizeKb();

    boolean hasVideosSizeKb();
}
