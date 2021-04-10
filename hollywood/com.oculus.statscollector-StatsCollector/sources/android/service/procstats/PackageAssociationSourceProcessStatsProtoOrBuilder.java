package android.service.procstats;

import android.service.procstats.PackageAssociationSourceProcessStatsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PackageAssociationSourceProcessStatsProtoOrBuilder extends MessageLiteOrBuilder {
    int getActiveCount();

    PackageAssociationSourceProcessStatsProto.StateStats getActiveStateStats(int i);

    int getActiveStateStatsCount();

    List<PackageAssociationSourceProcessStatsProto.StateStats> getActiveStateStatsList();

    String getPackageName();

    ByteString getPackageNameBytes();

    String getProcessName();

    ByteString getProcessNameBytes();

    int getProcessUid();

    int getTotalCount();

    long getTotalDurationMs();

    boolean hasActiveCount();

    boolean hasPackageName();

    boolean hasProcessName();

    boolean hasProcessUid();

    boolean hasTotalCount();

    boolean hasTotalDurationMs();
}
