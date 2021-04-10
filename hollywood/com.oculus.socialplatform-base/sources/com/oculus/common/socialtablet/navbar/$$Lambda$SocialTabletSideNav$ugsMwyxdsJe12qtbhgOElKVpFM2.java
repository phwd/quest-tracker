package com.oculus.common.socialtablet.navbar;

import android.service.notification.StatusBarNotification;
import com.oculus.common.socialtablet.notif.SocialNotificationUtils;
import java.util.function.Predicate;

/* renamed from: com.oculus.common.socialtablet.navbar.-$$Lambda$SocialTabletSideNav$u-gsMwyxdsJe12qtbhgOElKVpFM2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SocialTabletSideNav$ugsMwyxdsJe12qtbhgOElKVpFM2 implements Predicate {
    public static final /* synthetic */ $$Lambda$SocialTabletSideNav$ugsMwyxdsJe12qtbhgOElKVpFM2 INSTANCE = new $$Lambda$SocialTabletSideNav$ugsMwyxdsJe12qtbhgOElKVpFM2();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return SocialNotificationUtils.isMessagingNotification((StatusBarNotification) obj);
    }
}
