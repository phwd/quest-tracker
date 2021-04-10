package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface MessageProtoOrBuilder extends MessageLiteOrBuilder {
    int getArg1();

    int getArg2();

    int getBarrier();

    String getCallback();

    ByteString getCallbackBytes();

    String getObj();

    ByteString getObjBytes();

    String getTarget();

    ByteString getTargetBytes();

    int getWhat();

    long getWhen();

    boolean hasArg1();

    boolean hasArg2();

    boolean hasBarrier();

    boolean hasCallback();

    boolean hasObj();

    boolean hasTarget();

    boolean hasWhat();

    boolean hasWhen();
}
