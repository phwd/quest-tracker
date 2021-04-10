package android.service.print;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface ResolutionProtoOrBuilder extends MessageLiteOrBuilder {
    int getHorizontalDpi();

    String getId();

    ByteString getIdBytes();

    String getLabel();

    ByteString getLabelBytes();

    int getVerticalDpi();

    boolean hasHorizontalDpi();

    boolean hasId();

    boolean hasLabel();

    boolean hasVerticalDpi();
}
