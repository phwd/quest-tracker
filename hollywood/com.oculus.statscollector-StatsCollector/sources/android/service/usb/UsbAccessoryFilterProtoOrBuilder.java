package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbAccessoryFilterProtoOrBuilder extends MessageLiteOrBuilder {
    String getManufacturer();

    ByteString getManufacturerBytes();

    String getModel();

    ByteString getModelBytes();

    String getVersion();

    ByteString getVersionBytes();

    boolean hasManufacturer();

    boolean hasModel();

    boolean hasVersion();
}
