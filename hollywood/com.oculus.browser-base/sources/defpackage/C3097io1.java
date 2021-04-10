package defpackage;

import android.os.Bundle;

/* renamed from: io1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3097io1 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10163a;

    public C3097io1(boolean z) {
        this.f10163a = z;
    }

    public static C3097io1 a(Bundle bundle) {
        if (bundle.containsKey("android.support.customtabs.trusted.NOTIFICATION_SUCCESS")) {
            return new C3097io1(bundle.getBoolean("android.support.customtabs.trusted.NOTIFICATION_SUCCESS"));
        }
        throw new IllegalArgumentException(AbstractC2531fV.f("Bundle must contain ", "android.support.customtabs.trusted.NOTIFICATION_SUCCESS"));
    }
}
