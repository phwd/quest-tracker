package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BatteryStatsProtoOrBuilder extends MessageLiteOrBuilder {
    String getEndPlatformVersion();

    ByteString getEndPlatformVersionBytes();

    long getParcelVersion();

    int getReportVersion();

    String getStartPlatformVersion();

    ByteString getStartPlatformVersionBytes();

    SystemProto getSystem();

    UidProto getUids(int i);

    int getUidsCount();

    List<UidProto> getUidsList();

    boolean hasEndPlatformVersion();

    boolean hasParcelVersion();

    boolean hasReportVersion();

    boolean hasStartPlatformVersion();

    boolean hasSystem();
}
