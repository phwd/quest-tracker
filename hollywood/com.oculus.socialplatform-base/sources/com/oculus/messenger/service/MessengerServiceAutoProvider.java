package com.oculus.messenger.service;

import X.AnonymousClass0VK;

public class MessengerServiceAutoProvider extends AnonymousClass0VK<MessengerService> {
    public boolean equals(Object obj) {
        return obj instanceof MessengerServiceAutoProvider;
    }

    public void inject(MessengerService messengerService) {
        MessengerService._UL_staticInjectMe(this, messengerService);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        MessengerService._UL_staticInjectMe(this, (MessengerService) obj);
    }
}
