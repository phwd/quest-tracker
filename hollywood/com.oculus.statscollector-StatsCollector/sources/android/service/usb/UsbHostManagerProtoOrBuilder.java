package android.service.usb;

import android.content.ComponentNameProto;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbHostManagerProtoOrBuilder extends MessageLiteOrBuilder {
    UsbConnectionRecordProto getConnections(int i);

    int getConnectionsCount();

    List<UsbConnectionRecordProto> getConnectionsList();

    ComponentNameProto getDefaultUsbHostConnectionHandler();

    UsbDeviceProto getDevices(int i);

    int getDevicesCount();

    List<UsbDeviceProto> getDevicesList();

    int getNumConnects();

    boolean hasDefaultUsbHostConnectionHandler();

    boolean hasNumConnects();
}
