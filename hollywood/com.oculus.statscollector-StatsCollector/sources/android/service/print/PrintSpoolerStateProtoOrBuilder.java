package android.service.print;

import com.google.protobuf.MessageLiteOrBuilder;

public interface PrintSpoolerStateProtoOrBuilder extends MessageLiteOrBuilder {
    PrintSpoolerInternalStateProto getInternalState();

    boolean getIsBound();

    boolean getIsDestroyed();

    boolean hasInternalState();

    boolean hasIsBound();

    boolean hasIsDestroyed();
}
