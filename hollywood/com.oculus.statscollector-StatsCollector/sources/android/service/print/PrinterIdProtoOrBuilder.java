package android.service.print;

import android.content.ComponentNameProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface PrinterIdProtoOrBuilder extends MessageLiteOrBuilder {
    String getLocalId();

    ByteString getLocalIdBytes();

    ComponentNameProto getServiceName();

    boolean hasLocalId();

    boolean hasServiceName();
}
