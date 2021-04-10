package android.service.runtime;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface DebugEntryProtoOrBuilder extends MessageLiteOrBuilder {
    String getKey();

    ByteString getKeyBytes();

    String getStringValue();

    ByteString getStringValueBytes();

    boolean hasKey();

    boolean hasStringValue();
}
