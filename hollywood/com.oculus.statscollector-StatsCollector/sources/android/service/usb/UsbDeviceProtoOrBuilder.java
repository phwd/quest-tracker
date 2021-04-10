package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbDeviceProtoOrBuilder extends MessageLiteOrBuilder {
    int getClass_();

    UsbConfigurationProto getConfigurations(int i);

    int getConfigurationsCount();

    List<UsbConfigurationProto> getConfigurationsList();

    String getManufacturerName();

    ByteString getManufacturerNameBytes();

    String getName();

    ByteString getNameBytes();

    int getProductId();

    String getProductName();

    ByteString getProductNameBytes();

    int getProtocol();

    String getSerialNumber();

    ByteString getSerialNumberBytes();

    int getSubclass();

    int getVendorId();

    String getVersion();

    ByteString getVersionBytes();

    boolean hasClass_();

    boolean hasManufacturerName();

    boolean hasName();

    boolean hasProductId();

    boolean hasProductName();

    boolean hasProtocol();

    boolean hasSerialNumber();

    boolean hasSubclass();

    boolean hasVendorId();

    boolean hasVersion();
}
