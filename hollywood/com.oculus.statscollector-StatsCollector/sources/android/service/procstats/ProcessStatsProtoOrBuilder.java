package android.service.procstats;

import android.service.procstats.ProcessStatsProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ProcessStatsProtoOrBuilder extends MessageLiteOrBuilder {
    ProcessStatsProto.Kill getKill();

    String getProcess();

    ByteString getProcessBytes();

    ProcessStatsStateProto getStates(int i);

    int getStatesCount();

    List<ProcessStatsStateProto> getStatesList();

    ProcessStatsStateProto getTotalRunningState();

    int getUid();

    boolean hasKill();

    boolean hasProcess();

    boolean hasTotalRunningState();

    boolean hasUid();
}
