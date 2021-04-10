package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbAccessoryProtoOrBuilder extends MessageLiteOrBuilder {
    String getDescription();

    ByteString getDescriptionBytes();

    String getManufacturer();

    ByteString getManufacturerBytes();

    String getModel();

    ByteString getModelBytes();

    String getSerial();

    ByteString getSerialBytes();

    String getUri();

    ByteString getUriBytes();

    String getVersion();

    ByteString getVersionBytes();

    boolean hasDescription();

    boolean hasManufacturer();

    boolean hasModel();

    boolean hasSerial();

    boolean hasUri();

    boolean hasVersion();
}
