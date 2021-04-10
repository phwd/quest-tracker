package com.oculus.appmanager.installer.notification;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class NotificationUpdateInfoListenerAutoProvider extends AbstractProvider<NotificationUpdateInfoListener> {
    @Override // javax.inject.Provider
    public NotificationUpdateInfoListener get() {
        return new NotificationUpdateInfoListener(this);
    }
}
