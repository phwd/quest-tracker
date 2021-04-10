package android.service;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface NetworkStatsServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    NetworkInterfaceProto getActiveInterfaces(int i);

    int getActiveInterfacesCount();

    List<NetworkInterfaceProto> getActiveInterfacesList();

    NetworkInterfaceProto getActiveUidInterfaces(int i);

    int getActiveUidInterfacesCount();

    List<NetworkInterfaceProto> getActiveUidInterfacesList();

    NetworkStatsRecorderProto getDevStats();

    NetworkStatsRecorderProto getUidStats();

    NetworkStatsRecorderProto getUidTagStats();

    NetworkStatsRecorderProto getXtStats();

    boolean hasDevStats();

    boolean hasUidStats();

    boolean hasUidTagStats();

    boolean hasXtStats();
}
