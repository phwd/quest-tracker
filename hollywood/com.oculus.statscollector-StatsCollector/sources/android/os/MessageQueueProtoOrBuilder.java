package android.os;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface MessageQueueProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getIsPollingLocked();

    boolean getIsQuitting();

    MessageProto getMessages(int i);

    int getMessagesCount();

    List<MessageProto> getMessagesList();

    boolean hasIsPollingLocked();

    boolean hasIsQuitting();
}
