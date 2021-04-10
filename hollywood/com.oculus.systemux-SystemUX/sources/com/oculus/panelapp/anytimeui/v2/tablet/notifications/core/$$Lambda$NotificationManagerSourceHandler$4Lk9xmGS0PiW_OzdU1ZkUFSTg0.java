package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import java.util.Comparator;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.-$$Lambda$NotificationManagerSourceHandler$4Lk9xmGS-0PiW_OzdU1ZkUFSTg0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$NotificationManagerSourceHandler$4Lk9xmGS0PiW_OzdU1ZkUFSTg0 implements Comparator {
    public static final /* synthetic */ $$Lambda$NotificationManagerSourceHandler$4Lk9xmGS0PiW_OzdU1ZkUFSTg0 INSTANCE = new $$Lambda$NotificationManagerSourceHandler$4Lk9xmGS0PiW_OzdU1ZkUFSTg0();

    private /* synthetic */ $$Lambda$NotificationManagerSourceHandler$4Lk9xmGS0PiW_OzdU1ZkUFSTg0() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Long.compare(((NativeNotification) obj2).getPostedTimeSeconds(), ((NativeNotification) obj).getPostedTimeSeconds());
    }
}
