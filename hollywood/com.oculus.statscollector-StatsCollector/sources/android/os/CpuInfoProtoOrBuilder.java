package android.os;

import android.os.CpuInfoProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface CpuInfoProtoOrBuilder extends MessageLiteOrBuilder {
    CpuInfoProto.CpuUsage getCpuUsage();

    CpuInfoProto.MemStats getMem();

    CpuInfoProto.MemStats getSwap();

    CpuInfoProto.TaskStats getTaskStats();

    CpuInfoProto.Task getTasks(int i);

    int getTasksCount();

    List<CpuInfoProto.Task> getTasksList();

    boolean hasCpuUsage();

    boolean hasMem();

    boolean hasSwap();

    boolean hasTaskStats();
}
