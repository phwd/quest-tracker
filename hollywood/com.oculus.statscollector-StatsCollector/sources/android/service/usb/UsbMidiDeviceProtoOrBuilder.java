package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbMidiDeviceProtoOrBuilder extends MessageLiteOrBuilder {
    int getCard();

    int getDevice();

    String getDeviceAddress();

    ByteString getDeviceAddressBytes();

    boolean hasCard();

    boolean hasDevice();

    boolean hasDeviceAddress();
}
