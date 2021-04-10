package android.service.procstats;

import android.service.procstats.ProcessStatsSectionProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ProcessStatsSectionProtoOrBuilder extends MessageLiteOrBuilder {
    ProcessStatsAvailablePagesProto getAvailablePages(int i);

    int getAvailablePagesCount();

    List<ProcessStatsAvailablePagesProto> getAvailablePagesList();

    long getEndRealtimeMs();

    long getEndUptimeMs();

    boolean getHasSwappedPss();

    ProcessStatsPackageProto getPackageStats(int i);

    int getPackageStatsCount();

    List<ProcessStatsPackageProto> getPackageStatsList();

    ProcessStatsProto getProcessStats(int i);

    int getProcessStatsCount();

    List<ProcessStatsProto> getProcessStatsList();

    String getRuntime();

    ByteString getRuntimeBytes();

    long getStartRealtimeMs();

    long getStartUptimeMs();

    ProcessStatsSectionProto.Status getStatus(int i);

    int getStatusCount();

    List<ProcessStatsSectionProto.Status> getStatusList();

    boolean hasEndRealtimeMs();

    boolean hasEndUptimeMs();

    boolean hasHasSwappedPss();

    boolean hasRuntime();

    boolean hasStartRealtimeMs();

    boolean hasStartUptimeMs();
}
