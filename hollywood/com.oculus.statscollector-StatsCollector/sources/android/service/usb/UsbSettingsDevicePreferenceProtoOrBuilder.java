package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbSettingsDevicePreferenceProtoOrBuilder extends MessageLiteOrBuilder {
    UsbDeviceFilterProto getFilter();

    UserPackageProto getUserPackage();

    boolean hasFilter();

    boolean hasUserPackage();
}
