package android.os;

import android.os.BackTraceProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface BackTraceProtoOrBuilder extends MessageLiteOrBuilder {
    BackTraceProto.Stack getTraces(int i);

    int getTracesCount();

    List<BackTraceProto.Stack> getTracesList();
}
