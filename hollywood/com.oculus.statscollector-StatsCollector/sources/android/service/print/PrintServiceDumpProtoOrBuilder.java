package android.service.print;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface PrintServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    PrintUserStateProto getUserStates(int i);

    int getUserStatesCount();

    List<PrintUserStateProto> getUserStatesList();
}
