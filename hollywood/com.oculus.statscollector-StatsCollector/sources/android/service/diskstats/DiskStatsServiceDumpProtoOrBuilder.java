package android.service.diskstats;

import android.service.diskstats.DiskStatsServiceDumpProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface DiskStatsServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    int getBenchmarkedWriteSpeedKbps();

    DiskStatsCachedValuesProto getCachedFolderSizes();

    DiskStatsServiceDumpProto.EncryptionType getEncryption();

    String getErrorMessage();

    ByteString getErrorMessageBytes();

    boolean getHasTestError();

    DiskStatsFreeSpaceProto getPartitionsFreeSpace(int i);

    int getPartitionsFreeSpaceCount();

    List<DiskStatsFreeSpaceProto> getPartitionsFreeSpaceList();

    int getWrite512BLatencyMillis();

    boolean hasBenchmarkedWriteSpeedKbps();

    boolean hasCachedFolderSizes();

    boolean hasEncryption();

    boolean hasErrorMessage();

    boolean hasHasTestError();

    boolean hasWrite512BLatencyMillis();
}
