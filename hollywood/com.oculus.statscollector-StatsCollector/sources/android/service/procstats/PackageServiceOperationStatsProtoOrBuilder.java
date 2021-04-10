package android.service.procstats;

import android.service.procstats.PackageServiceOperationStatsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PackageServiceOperationStatsProtoOrBuilder extends MessageLiteOrBuilder {
    int getCount();

    ServiceOperationState getOperation();

    PackageServiceOperationStatsProto.StateStats getStateStats(int i);

    int getStateStatsCount();

    List<PackageServiceOperationStatsProto.StateStats> getStateStatsList();

    boolean hasCount();

    boolean hasOperation();
}
