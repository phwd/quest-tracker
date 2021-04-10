package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbServiceDumpProtoOrBuilder extends MessageLiteOrBuilder {
    UsbAlsaManagerProto getAlsaManager();

    UsbDeviceManagerProto getDeviceManager();

    UsbHostManagerProto getHostManager();

    UsbPortManagerProto getPortManager();

    UsbSettingsManagerProto getSettingsManager();

    boolean hasAlsaManager();

    boolean hasDeviceManager();

    boolean hasHostManager();

    boolean hasPortManager();

    boolean hasSettingsManager();
}
