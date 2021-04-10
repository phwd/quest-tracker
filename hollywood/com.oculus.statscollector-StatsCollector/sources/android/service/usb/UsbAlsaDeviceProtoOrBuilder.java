package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbAlsaDeviceProtoOrBuilder extends MessageLiteOrBuilder {
    String getAddress();

    ByteString getAddressBytes();

    int getCard();

    int getDevice();

    boolean getHasCapture();

    boolean getHasPlayback();

    String getName();

    ByteString getNameBytes();

    boolean hasAddress();

    boolean hasCard();

    boolean hasDevice();

    boolean hasHasCapture();

    boolean hasHasPlayback();

    boolean hasName();
}
