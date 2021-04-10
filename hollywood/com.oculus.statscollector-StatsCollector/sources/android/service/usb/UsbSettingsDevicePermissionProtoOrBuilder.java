package android.service.usb;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbSettingsDevicePermissionProtoOrBuilder extends MessageLiteOrBuilder {
    String getDeviceName();

    ByteString getDeviceNameBytes();

    int getUids(int i);

    int getUidsCount();

    List<Integer> getUidsList();

    boolean hasDeviceName();
}
