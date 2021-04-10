package android.service.usb;

import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface UsbUserSettingsManagerProtoOrBuilder extends MessageLiteOrBuilder {
    UsbAccessoryAttachedActivities getAccessoryAttachedActivities(int i);

    int getAccessoryAttachedActivitiesCount();

    List<UsbAccessoryAttachedActivities> getAccessoryAttachedActivitiesList();

    UsbSettingsAccessoryPermissionProto getAccessoryPermissions(int i);

    int getAccessoryPermissionsCount();

    List<UsbSettingsAccessoryPermissionProto> getAccessoryPermissionsList();

    UsbDeviceAttachedActivities getDeviceAttachedActivities(int i);

    int getDeviceAttachedActivitiesCount();

    List<UsbDeviceAttachedActivities> getDeviceAttachedActivitiesList();

    UsbSettingsDevicePermissionProto getDevicePermissions(int i);

    int getDevicePermissionsCount();

    List<UsbSettingsDevicePermissionProto> getDevicePermissionsList();

    int getUserId();

    boolean hasUserId();
}
