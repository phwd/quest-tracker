package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbSettingsManagerProtoOrBuilder extends MessageLiteOrBuilder {
    UsbProfileGroupSettingsManagerProto getProfileGroupSettings(int i);

    int getProfileGroupSettingsCount();

    List<UsbProfileGroupSettingsManagerProto> getProfileGroupSettingsList();

    UsbUserSettingsManagerProto getUserSettings(int i);

    int getUserSettingsCount();

    List<UsbUserSettingsManagerProto> getUserSettingsList();
}
