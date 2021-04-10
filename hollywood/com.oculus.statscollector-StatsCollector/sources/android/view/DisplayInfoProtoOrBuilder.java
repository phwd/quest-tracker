package android.view;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface DisplayInfoProtoOrBuilder extends MessageLiteOrBuilder {
    int getAppHeight();

    int getAppWidth();

    int getLogicalHeight();

    int getLogicalWidth();

    String getName();

    ByteString getNameBytes();

    boolean hasAppHeight();

    boolean hasAppWidth();

    boolean hasLogicalHeight();

    boolean hasLogicalWidth();

    boolean hasName();
}
