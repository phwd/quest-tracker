package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: C  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C {

    /* renamed from: a  reason: collision with root package name */
    public final Object f7778a;

    public C(Object obj) {
        this.f7778a = obj;
    }

    public static C a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        return new C(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
    }
}
