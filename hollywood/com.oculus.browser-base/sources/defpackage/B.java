package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: B  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B {

    /* renamed from: a  reason: collision with root package name */
    public final Object f7712a;

    public B(Object obj) {
        this.f7712a = obj;
    }

    public static B a(int i, int i2, boolean z, int i3) {
        return new B(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3));
    }
}
