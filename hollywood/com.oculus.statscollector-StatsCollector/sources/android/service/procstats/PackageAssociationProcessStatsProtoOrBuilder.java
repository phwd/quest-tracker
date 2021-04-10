package android.service.procstats;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PackageAssociationProcessStatsProtoOrBuilder extends MessageLiteOrBuilder {
    String getComponentName();

    ByteString getComponentNameBytes();

    PackageAssociationSourceProcessStatsProto getSources(int i);

    int getSourcesCount();

    List<PackageAssociationSourceProcessStatsProto> getSourcesList();

    boolean hasComponentName();
}
