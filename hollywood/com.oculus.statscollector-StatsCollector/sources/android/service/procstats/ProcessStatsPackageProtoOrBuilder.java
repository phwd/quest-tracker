package android.service.procstats;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ProcessStatsPackageProtoOrBuilder extends MessageLiteOrBuilder {
    PackageAssociationProcessStatsProto getAssociationStats(int i);

    int getAssociationStatsCount();

    List<PackageAssociationProcessStatsProto> getAssociationStatsList();

    String getPackage();

    ByteString getPackageBytes();

    ProcessStatsProto getProcessStats(int i);

    int getProcessStatsCount();

    List<ProcessStatsProto> getProcessStatsList();

    PackageServiceStatsProto getServiceStats(int i);

    int getServiceStatsCount();

    List<PackageServiceStatsProto> getServiceStatsList();

    int getUid();

    long getVersion();

    boolean hasPackage();

    boolean hasUid();

    boolean hasVersion();
}
