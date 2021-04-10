package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbPortManagerProtoOrBuilder extends MessageLiteOrBuilder {
    boolean getIsSimulationActive();

    UsbPortInfoProto getUsbPorts(int i);

    int getUsbPortsCount();

    List<UsbPortInfoProto> getUsbPortsList();

    boolean hasIsSimulationActive();
}
