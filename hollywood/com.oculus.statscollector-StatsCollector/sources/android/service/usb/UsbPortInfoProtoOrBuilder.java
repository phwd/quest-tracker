package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;

public interface UsbPortInfoProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getCanChangeDataRole();

    boolean getCanChangeMode();

    boolean getCanChangePowerRole();

    long getConnectedAtMillis();

    long getLastConnectDurationMillis();

    UsbPortProto getPort();

    UsbPortStatusProto getStatus();

    boolean hasCanChangeDataRole();

    boolean hasCanChangeMode();

    boolean hasCanChangePowerRole();

    boolean hasConnectedAtMillis();

    boolean hasLastConnectDurationMillis();

    boolean hasPort();

    boolean hasStatus();
}
