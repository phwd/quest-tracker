package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbAlsaManagerProtoOrBuilder extends MessageLiteOrBuilder {
    UsbAlsaDeviceProto getAlsaDevices(int i);

    int getAlsaDevicesCount();

    List<UsbAlsaDeviceProto> getAlsaDevicesList();

    int getCardsParser();

    UsbMidiDeviceProto getMidiDevices(int i);

    int getMidiDevicesCount();

    List<UsbMidiDeviceProto> getMidiDevicesList();

    boolean hasCardsParser();
}
