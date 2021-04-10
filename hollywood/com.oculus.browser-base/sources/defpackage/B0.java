package defpackage;

import android.view.ViewGroup;

/* renamed from: B0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class B0 {
    public static AbstractC5180v0 a(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new A0(viewGroup);
        }
        if (i == 6) {
            return new C5520x0(viewGroup);
        }
        if (i != 8) {
            return null;
        }
        return new C5860z0(viewGroup);
    }
}
