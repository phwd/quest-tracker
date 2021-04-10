package com.oculus.appmanager.installer.notification;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class NotificationDownloadListenerAutoProvider extends AbstractProvider<NotificationDownloadListener> {
    @Override // javax.inject.Provider
    public NotificationDownloadListener get() {
        return new NotificationDownloadListener(this);
    }
}
