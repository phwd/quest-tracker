package android.service.batterystats;

import android.service.batterystats.BatteryStatsServiceDumpHistoryProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BatteryStatsServiceDumpHistoryProtoOrBuilder extends MessageLiteOrBuilder {
    String getCsvLines(int i);

    ByteString getCsvLinesBytes(int i);

    int getCsvLinesCount();

    List<String> getCsvLinesList();

    String getEndPlatformVersion();

    ByteString getEndPlatformVersionBytes();

    BatteryStatsServiceDumpHistoryProto.Key getKeys(int i);

    int getKeysCount();

    List<BatteryStatsServiceDumpHistoryProto.Key> getKeysList();

    long getParcelVersion();

    int getReportVersion();

    String getStartPlatformVersion();

    ByteString getStartPlatformVersionBytes();

    boolean hasEndPlatformVersion();

    boolean hasParcelVersion();

    boolean hasReportVersion();

    boolean hasStartPlatformVersion();
}
