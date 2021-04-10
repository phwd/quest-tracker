package android.os;

import android.os.WorkSourceProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface WorkSourceProtoOrBuilder extends MessageLiteOrBuilder {
    WorkSourceProto.WorkChain getWorkChains(int i);

    int getWorkChainsCount();

    List<WorkSourceProto.WorkChain> getWorkChainsList();

    WorkSourceProto.WorkSourceContentProto getWorkSourceContents(int i);

    int getWorkSourceContentsCount();

    List<WorkSourceProto.WorkSourceContentProto> getWorkSourceContentsList();
}
