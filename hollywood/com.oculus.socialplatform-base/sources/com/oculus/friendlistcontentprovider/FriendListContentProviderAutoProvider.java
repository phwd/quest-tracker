package com.oculus.friendlistcontentprovider;

import X.AnonymousClass0VK;
import X.AnonymousClass0lg;

public class FriendListContentProviderAutoProvider extends AnonymousClass0VK<FriendListContentProvider> {
    public boolean equals(Object obj) {
        return obj instanceof FriendListContentProviderAutoProvider;
    }

    public void inject(FriendListContentProvider friendListContentProvider) {
        FriendListContentProvider._UL_staticInjectMe((AnonymousClass0lg) this, friendListContentProvider);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        FriendListContentProvider._UL_staticInjectMe((AnonymousClass0lg) this, (FriendListContentProvider) obj);
    }
}
