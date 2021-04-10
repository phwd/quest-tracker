package android.service.procstats;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PackageServiceStatsProtoOrBuilder extends MessageLiteOrBuilder {
    PackageServiceOperationStatsProto getOperationStats(int i);

    int getOperationStatsCount();

    List<PackageServiceOperationStatsProto> getOperationStatsList();

    String getServiceName();

    ByteString getServiceNameBytes();

    boolean hasServiceName();
}
