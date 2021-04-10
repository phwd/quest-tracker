package android.os;

import android.os.PsProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PsProtoOrBuilder extends MessageLiteOrBuilder {
    PsProto.Process getProcesses(int i);

    int getProcessesCount();

    List<PsProto.Process> getProcessesList();
}
