package android.service.runtime;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface RuntimeServiceInfoProtoOrBuilder extends MessageLiteOrBuilder {
    DebugEntryProto getDebugEntry(int i);

    int getDebugEntryCount();

    List<DebugEntryProto> getDebugEntryList();
}
