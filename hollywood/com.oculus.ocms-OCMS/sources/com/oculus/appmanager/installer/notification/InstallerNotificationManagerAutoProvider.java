package com.oculus.appmanager.installer.notification;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.util.device.DeviceUtils;

@Generated({"By: InjectorProcessor"})
public class InstallerNotificationManagerAutoProvider extends AbstractProvider<InstallerNotificationManager> {
    @Override // javax.inject.Provider
    public InstallerNotificationManager get() {
        return new InstallerNotificationManager(this, DeviceUtils._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_ACCESS_METHOD(this));
    }
}
