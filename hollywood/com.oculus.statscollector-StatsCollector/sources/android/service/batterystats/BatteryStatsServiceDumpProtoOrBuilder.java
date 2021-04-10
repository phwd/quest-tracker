package android.service.batterystats;

import android.os.BatteryStatsProto;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BatteryStatsServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    BatteryStatsProto getBatterystats();

    boolean hasBatterystats();
}
