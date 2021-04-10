package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface LooperProtoOrBuilder extends MessageLiteOrBuilder {
    MessageQueueProto getQueue();

    long getThreadId();

    String getThreadName();

    ByteString getThreadNameBytes();

    boolean hasQueue();

    boolean hasThreadId();

    boolean hasThreadName();
}
