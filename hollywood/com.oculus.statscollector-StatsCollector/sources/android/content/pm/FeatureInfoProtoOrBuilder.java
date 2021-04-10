package android.content.pm;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface FeatureInfoProtoOrBuilder extends MessageLiteOrBuilder {
    int getFlags();

    String getGlesVersion();

    ByteString getGlesVersionBytes();

    String getName();

    ByteString getNameBytes();

    int getVersion();

    boolean hasFlags();

    boolean hasGlesVersion();

    boolean hasName();

    boolean hasVersion();
}
