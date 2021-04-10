package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbDeviceFilterProtoOrBuilder extends MessageLiteOrBuilder {
    int getClass_();

    String getManufacturerName();

    ByteString getManufacturerNameBytes();

    int getProductId();

    String getProductName();

    ByteString getProductNameBytes();

    int getProtocol();

    String getSerialNumber();

    ByteString getSerialNumberBytes();

    int getSubclass();

    int getVendorId();

    boolean hasClass_();

    boolean hasManufacturerName();

    boolean hasProductId();

    boolean hasProductName();

    boolean hasProtocol();

    boolean hasSerialNumber();

    boolean hasSubclass();

    boolean hasVendorId();
}
