package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbConfigurationProtoOrBuilder extends MessageLiteOrBuilder {
    int getAttributes();

    int getId();

    UsbInterfaceProto getInterfaces(int i);

    int getInterfacesCount();

    List<UsbInterfaceProto> getInterfacesList();

    int getMaxPower();

    String getName();

    ByteString getNameBytes();

    boolean hasAttributes();

    boolean hasId();

    boolean hasMaxPower();

    boolean hasName();
}
