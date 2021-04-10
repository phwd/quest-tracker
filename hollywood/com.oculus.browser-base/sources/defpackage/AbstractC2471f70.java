package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: f70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2471f70 {
    public static View a(Context context, int i, ViewGroup viewGroup) {
        boolean z = viewGroup != null;
        LayoutInflater from = LayoutInflater.from(context);
        P21 f0 = P21.f0();
        try {
            View inflate = from.inflate(i, viewGroup, z);
            f0.close();
            return inflate;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
