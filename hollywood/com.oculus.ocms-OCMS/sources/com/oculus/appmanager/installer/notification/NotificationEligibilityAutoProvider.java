package com.oculus.appmanager.installer.notification;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class NotificationEligibilityAutoProvider extends AbstractProvider<NotificationEligibility> {
    @Override // javax.inject.Provider
    public NotificationEligibility get() {
        return new NotificationEligibility(this);
    }
}
