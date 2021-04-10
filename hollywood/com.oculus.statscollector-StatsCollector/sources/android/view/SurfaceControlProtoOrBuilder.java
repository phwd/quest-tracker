package android.view;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface SurfaceControlProtoOrBuilder extends MessageLiteOrBuilder {
    int getHashCode();

    String getName();

    ByteString getNameBytes();

    boolean hasHashCode();

    boolean hasName();
}
