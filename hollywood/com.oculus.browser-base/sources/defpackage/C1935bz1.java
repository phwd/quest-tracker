package defpackage;

import android.view.View;
import android.view.WindowId;

/* renamed from: bz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1935bz1 implements AbstractC2106cz1 {

    /* renamed from: a  reason: collision with root package name */
    public final WindowId f9577a;

    public C1935bz1(View view) {
        this.f9577a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof C1935bz1) && ((C1935bz1) obj).f9577a.equals(this.f9577a);
    }

    public int hashCode() {
        return this.f9577a.hashCode();
    }
}
