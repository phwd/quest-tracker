package android.service.usb;

import android.service.UsbConnectionRecordMode;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbConnectionRecordProtoOrBuilder extends MessageLiteOrBuilder {
    String getDeviceAddress();

    ByteString getDeviceAddressBytes();

    UsbIsHeadsetProto getIsHeadset();

    int getManufacturer();

    UsbConnectionRecordMode getMode();

    int getProduct();

    long getTimestamp();

    boolean hasDeviceAddress();

    boolean hasIsHeadset();

    boolean hasManufacturer();

    boolean hasMode();

    boolean hasProduct();

    boolean hasTimestamp();
}
