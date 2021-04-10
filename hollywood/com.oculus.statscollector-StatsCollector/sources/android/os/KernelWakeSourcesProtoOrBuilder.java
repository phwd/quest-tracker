package android.os;

import android.os.KernelWakeSourcesProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface KernelWakeSourcesProtoOrBuilder extends MessageLiteOrBuilder {
    KernelWakeSourcesProto.WakeupSource getWakeupSources(int i);

    int getWakeupSourcesCount();

    List<KernelWakeSourcesProto.WakeupSource> getWakeupSourcesList();
}
