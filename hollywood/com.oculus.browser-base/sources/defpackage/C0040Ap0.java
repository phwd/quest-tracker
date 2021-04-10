package defpackage;

import android.os.Build;

/* renamed from: Ap0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0040Ap0 {

    /* renamed from: a  reason: collision with root package name */
    public final C2756go1 f7699a;
    public final MX0 b;

    public C0040Ap0(C2756go1 go1, MX0 mx0) {
        this.f7699a = go1;
        this.b = mx0;
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT < 26;
    }
}
