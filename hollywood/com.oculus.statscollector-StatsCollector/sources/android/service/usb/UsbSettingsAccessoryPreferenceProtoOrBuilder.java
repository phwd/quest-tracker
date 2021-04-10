package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbSettingsAccessoryPreferenceProtoOrBuilder extends MessageLiteOrBuilder {
    UsbAccessoryFilterProto getFilter();

    UserPackageProto getUserPackage();

    boolean hasFilter();

    boolean hasUserPackage();
}
