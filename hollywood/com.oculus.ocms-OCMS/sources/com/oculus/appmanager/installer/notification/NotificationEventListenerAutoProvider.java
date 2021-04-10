package com.oculus.appmanager.installer.notification;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class NotificationEventListenerAutoProvider extends AbstractProvider<NotificationEventListener> {
    @Override // javax.inject.Provider
    public NotificationEventListener get() {
        return new NotificationEventListener(this);
    }
}
