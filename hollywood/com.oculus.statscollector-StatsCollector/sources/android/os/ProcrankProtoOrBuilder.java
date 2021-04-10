package android.os;

import android.os.ProcrankProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface ProcrankProtoOrBuilder extends MessageLiteOrBuilder {
    ProcrankProto.Process getProcesses(int i);

    int getProcessesCount();

    List<ProcrankProto.Process> getProcessesList();

    ProcrankProto.Summary getSummary();

    boolean hasSummary();
}
