package android.app;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PendingIntentProtoOrBuilder extends MessageLiteOrBuilder {
    String getTarget();

    ByteString getTargetBytes();

    boolean hasTarget();
}
