package android.os;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface BatteryTypeProtoOrBuilder extends MessageLiteOrBuilder {
    String getType();

    ByteString getTypeBytes();

    boolean hasType();
}
