package com.oculus.appmanager.installer.notification.contract;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class InstallerNotificationsContractAutoProvider extends AbstractProvider<InstallerNotificationsContract> {
    @Override // javax.inject.Provider
    public InstallerNotificationsContract get() {
        return new InstallerNotificationsContract(this);
    }
}
