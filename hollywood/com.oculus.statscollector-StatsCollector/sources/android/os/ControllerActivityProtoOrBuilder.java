package android.os;

import android.os.ControllerActivityProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ControllerActivityProtoOrBuilder extends MessageLiteOrBuilder {
    long getIdleDurationMs();

    double getMonitoredRailChargeMah();

    long getPowerMah();

    long getRxDurationMs();

    ControllerActivityProto.TxLevel getTx(int i);

    int getTxCount();

    List<ControllerActivityProto.TxLevel> getTxList();

    boolean hasIdleDurationMs();

    boolean hasMonitoredRailChargeMah();

    boolean hasPowerMah();

    boolean hasRxDurationMs();
}
