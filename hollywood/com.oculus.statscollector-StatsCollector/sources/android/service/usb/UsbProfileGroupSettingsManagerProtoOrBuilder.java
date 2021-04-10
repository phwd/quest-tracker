package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbProfileGroupSettingsManagerProtoOrBuilder extends MessageLiteOrBuilder {
    UsbSettingsAccessoryPreferenceProto getAccessoryPreferences(int i);

    int getAccessoryPreferencesCount();

    List<UsbSettingsAccessoryPreferenceProto> getAccessoryPreferencesList();

    UsbSettingsDevicePreferenceProto getDevicePreferences(int i);

    int getDevicePreferencesCount();

    List<UsbSettingsDevicePreferenceProto> getDevicePreferencesList();

    int getParentUserId();

    boolean hasParentUserId();
}
