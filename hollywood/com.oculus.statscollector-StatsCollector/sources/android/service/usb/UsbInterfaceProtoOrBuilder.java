package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbInterfaceProtoOrBuilder extends MessageLiteOrBuilder {
    int getAlternateSettings();

    int getClass_();

    UsbEndPointProto getEndpoints(int i);

    int getEndpointsCount();

    List<UsbEndPointProto> getEndpointsList();

    int getId();

    String getName();

    ByteString getNameBytes();

    int getProtocol();

    int getSubclass();

    boolean hasAlternateSettings();

    boolean hasClass_();

    boolean hasId();

    boolean hasName();

    boolean hasProtocol();

    boolean hasSubclass();
}
