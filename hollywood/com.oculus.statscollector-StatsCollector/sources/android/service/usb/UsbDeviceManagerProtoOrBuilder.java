package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbDeviceManagerProtoOrBuilder extends MessageLiteOrBuilder {
    UsbDebuggingManagerProto getDebuggingManager();

    UsbHandlerProto getHandler();

    boolean hasDebuggingManager();

    boolean hasHandler();
}
