package android.service.print;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface MediaSizeProtoOrBuilder extends MessageLiteOrBuilder {
    int getHeightMils();

    String getId();

    ByteString getIdBytes();

    String getLabel();

    ByteString getLabelBytes();

    int getWidthMils();

    boolean hasHeightMils();

    boolean hasId();

    boolean hasLabel();

    boolean hasWidthMils();
}
